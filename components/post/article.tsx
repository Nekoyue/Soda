import {remark} from 'remark'
import remarkHtml from 'remark-html'
import {IPost, PostProps} from "../../interfaces/post"
import fs from "fs"
import styles from "../../styles/Post.module.scss"
import matter from 'gray-matter'
import remarkGfm from "remark-gfm"


const Article = ({post}: PostProps) => {
    return (<>
        <h1 className={styles.title}>{post.title}</h1>
        <p className={styles.description}>{post.description}</p>
        <div className={styles.metadata}>
            {/* TODO: Add dividers conditionally, author and createAt are nullable */}
            <span className={styles.metadata_dot}>•</span>
            <span className={styles.metadata_author}>{post.author}</span>
            <span className={styles.metadata_divider}>|</span>
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
    const [markdown] = await Promise.all([remark()
        .use(remarkGfm)
        .use(remarkHtml)
        .process(content)])

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
