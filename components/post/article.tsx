import {IPost, PostProps} from "../../interfaces/post"
import fs from "fs"
import styles from "../../styles/Post.module.scss"
import matter from 'gray-matter'
import remarkGfm from "remark-gfm"
import rehypeRaw from "rehype-raw"
import remarkRehype from "remark-rehype"
import {unified} from "unified"
import remarkParse from "remark-parse"
import rehypePrism from 'rehype-prism-plus'
import rehypeStringify from "rehype-stringify"
import rehypeSanitize, {defaultSchema} from "rehype-sanitize"
import rehypeFormat from "rehype-format"


const Article = ({post}: PostProps) => {
    return (<>
        <h1 className={styles.title}>{post.title}</h1>
        <p className={styles.description}>{post.description}</p>
        <div className={styles.metadata}>
            {/* TODO: Add dividers conditionally, author and createAt are nullable */}
            <span className={styles.metadata_author}>{post.author}</span>
            <span className={styles.metadata_time}>{post.createAt}</span>
        </div>
        <div
            className={styles.article}
            dangerouslySetInnerHTML={{__html: post.markdownHTML}}
        />
    </>)
}

export default Article

export async function markdownToIPost(rawMarkdown: string): Promise<Post> {
    const {data, content} = matter(rawMarkdown)
    const markdown = await unified()
        .use(remarkParse)
        .use(remarkGfm)
        .use(remarkRehype, {allowDangerousHtml: true})
        .use(rehypeRaw)
        .use(rehypeSanitize, {
            ...defaultSchema, attributes: { // allow className for code highlighting
                ...defaultSchema.attributes,
                pre: [...(defaultSchema.attributes?.pre || []), 'className'],
                code: [...(defaultSchema.attributes?.code || []), 'className']
            }
        }) // prevent XSS attacks
        .use(rehypeFormat)
        .use(rehypeStringify)
        .use(rehypePrism)
        .process(content)


    return new Post(data["title"], String(markdown), data["description"], data["author"], data["date"])
}

export function getAllPostIdentifiers() {
    return fs.readdirSync("_posts/").map((it) => it.replace(/\.md$/, ''))
}

export function getMarkdownFromIdentifier(identifier: string) {
    return fs.readFileSync(`_posts/${identifier}.md`, 'utf8')
}

export function getAllPostMarkdowns() {
    const identifiers = getAllPostIdentifiers()
    return identifiers.map((identifier) => getMarkdownFromIdentifier(identifier))
}


class Post implements IPost {
    createAt?: string

    constructor(readonly title: string,
                readonly markdownHTML: string,
                readonly description?: string,
                readonly author?: string,
                createAt?: string) {
        if (createAt) {
            // format date to "December 22, 2022"
            this.createAt = new Date(createAt).toLocaleDateString("en-CA",
                {year: 'numeric', month: 'long', day: 'numeric'}
            )
        }
    }
}
