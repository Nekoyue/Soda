import Container from "../../components/container"
import styles from "../../styles/Post.module.scss"
import Article, {getAllPostIdentifiers, getMarkdownFromIdentifier, markdownToIPost} from "../../components/post/article"
import {PostProps} from "../../interfaces/post"
import Head from "next/head"
import {title} from "../../_data/metadata"
import React from "react"


export default function Post({post}: PostProps) {
    return <Container>
        <Head>
            <title key="title">{`${post.title} | ${title}`}</title>
            <meta property="og:title" content={`${post.title} | ${title}`} key="ogtitle"/>
            <meta name="description" content={post.description} key="description"/>
            <meta property="og:description" content={post.description} key="ogdescription"/>
        </Head>
        <article className={styles.root}>
            <Article post={post}/>
        </article>
    </Container>
}

type Params = {
    params: {
        identifier: string
    }
}

export async function getStaticProps({params}: Params) {
    const rawMarkdown = getMarkdownFromIdentifier(params.identifier)
    const post = await markdownToIPost(rawMarkdown)
    return {
        props: {
            post: {
                title: post.title,
                markdownHTML: post.markdownHTML,
                description: post.description || null,
                createAt: post.createAt || null,
                author: post.author || null
            }
        }
    }
}

export async function getStaticPaths() {
    const identifiers = getAllPostIdentifiers()
    return {
        paths: identifiers.map((identifier) => {
            return {params: {identifier: identifier}}
        }),
        fallback: false
    }
}
