import matter from "gray-matter"
import {unified} from "unified"
import remarkParse from "remark-parse"
import remarkGfm from "remark-gfm"
import remarkRehype from "remark-rehype"
import rehypeRaw from "rehype-raw"
import rehypeSanitize, {defaultSchema} from "rehype-sanitize"
import rehypeFormat from "rehype-format"
import rehypeHighlight from "rehype-highlight"
import fs from "fs"
import {IPost} from "./IPost"
import rehypeReact from "rehype-react";
import Link from "next/link";
import * as production from 'react/jsx-runtime'
import {ReactElement} from "react";

export async function markdownToIPost(rawMarkdown: string): Promise<Post> {
    const {data, content} = matter(rawMarkdown)
    const markdown = unified()
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
        .use(rehypeHighlight)
        .use(rehypeReact, {...production, components: {a: Link}})
        .processSync(content).result

    return new Post(data["title"], markdown, data["description"], data["author"], data["date"])
}

export function getAllPostIdentifiers() {
    return fs.readdirSync(`${process.cwd()}/app/_posts/`).map((it) => it.replace(/\.md$/, ''))
}

export function getMarkdownFromIdentifier(identifier: string) {
    return fs.readFileSync(`${process.cwd()}/app/_posts/${identifier}.md`, 'utf8')
}

export function getAllPostMarkdowns() {
    const identifiers = getAllPostIdentifiers()
    return identifiers.map((identifier) => getMarkdownFromIdentifier(identifier))
}


class Post implements IPost {
    createAt?: string

    constructor(readonly title: string,
                readonly markdownReact: ReactElement,
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
