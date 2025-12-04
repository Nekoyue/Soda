import fs from "fs"
import remarkGfm from "remark-gfm"
import {IPost} from "./IPost"
import {ReactElement} from "react";
import {compileMDX} from 'next-mdx-remote/rsc'
import rehypeRaw from "rehype-raw";
import rehypeFormat from "rehype-format";
import rehypeHighlight from "rehype-highlight";
import Link from "next/link";
import rehypeAutolinkHeadings from "rehype-autolink-headings";
import {nodeTypes} from "@mdx-js/mdx";

export async function markdownToIPost(rawMarkdown: string): Promise<Post> {
    const {content, frontmatter} = await compileMDX<{
        title: string, description: string | undefined, author: string | undefined, date: string | undefined
    }>
    ({
        source: rawMarkdown,
        options: {
            mdxOptions: {
                remarkPlugins: [remarkGfm],
                rehypePlugins: [
                    [rehypeRaw, {passThrough: nodeTypes}],
                    // [rehypeSanitize, { // prevent XSS attacks
                    //     ...defaultSchema, attributes: { // allow className for code highlighting
                    //         ...defaultSchema.attributes,
                    //         pre: [...(defaultSchema.attributes?.pre || []), 'className'],
                    //         code: [...(defaultSchema.attributes?.code || []), 'className']
                    //     }
                    // }],
                    rehypeFormat,
                    rehypeAutolinkHeadings,
                    rehypeHighlight,
                ],
                format: "md"
            },
            parseFrontmatter: true
        },
        components: {
            a(props) {
                return <Link {...props} />
            } // <Link/> provides client-side optimization
        }
    })

    return new Post(frontmatter.title, content,
        frontmatter.description, frontmatter.author, frontmatter.date)
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
