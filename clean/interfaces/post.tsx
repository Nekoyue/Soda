export interface IPost {
    title: string,
    markdownHTML: string,
    description?: string,
    author?: string,
    createAt?: string
}

export type PostProps = {
    post: IPost
}
