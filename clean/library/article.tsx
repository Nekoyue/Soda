import matter from "gray-matter"
import {unified} from "unified"
import remarkParse from "remark-parse"
import remarkGfm from "remark-gfm"
import remarkRehype from "remark-rehype"
import rehypeRaw from "rehype-raw"
import rehypeSanitize, {defaultSchema} from "rehype-sanitize"
import rehypeFormat from "rehype-format"
import rehypeStringify from "rehype-stringify"
import rehypePrism from "rehype-prism-plus"
import fs from "fs"
import {IPost} from "../interfaces/post"

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
