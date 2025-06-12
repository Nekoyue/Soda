import styles from "./Post.module.scss"
import {title} from "@/app/_data/metadata"
import React, {cache, ReactElement, useMemo} from "react"
import {getAllPostIdentifiers, getMarkdownFromIdentifier, markdownToIPost} from "@/app/posts/[identifier]/article";
import {PostProps} from "@/app/posts/[identifier]/IPost";

export async function generateMetadata(props: { params: Promise<{ identifier: string }> }) {
    const params = await props.params;
    const {post} = await getPost(params.identifier)
    return {
        title: `${post.title} | ${title}`,
        description: post.description,
        openGraph: {
            title: `${post.title} | ${title}`,
            description: post.description
        }
    }
}

export default async function Post(props: { params: Promise<{ identifier: string }> }) {
    const params = await props.params;
    const {post} = await getPost(params.identifier)
    return <>
        <article className={styles.root}>
            <Article post={post}/>
        </article>
    </>
}

const Article = ({post}: PostProps) => {
    const article = useMemo(() => post.markdownReact, [post?.markdownReact]) as ReactElement;

    return (<>
        <h1 className={styles.title}>{post.title}</h1>
        <p className={styles.description}>{post.description}</p>
        <div className={styles.metadata}>
            {/* TODO: Add dividers conditionally, author and createAt are nullable */}
            <span className={styles.metadata_author}>{post.author}</span>
            <span className={styles.metadata_time}>{post.createAt}</span>
        </div>
        <div className={styles.article}>{article}</div>
    </>)
}

const getPost = cache(async (identifier: string): Promise<PostProps> => {
    const rawMarkdown = getMarkdownFromIdentifier(identifier)
    const post = await markdownToIPost(rawMarkdown)
    return {
        post: {
            title: post.title,
            markdownReact: post.markdownReact,
            description: post.description || undefined,
            createAt: post.createAt || undefined,
            author: post.author || undefined
        }
    }
})

export async function generateStaticParams() {
    const identifiers = getAllPostIdentifiers()
    return identifiers.map((identifier) => ({identifier: identifier}))
}
