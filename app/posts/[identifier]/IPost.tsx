import {ReactElement} from "react";

export interface IPost {
    title: string,
    markdownReact: ReactElement,
    description?: string,
    author?: string,
    createAt?: string
}

export type PostProps = {
    post: IPost
}
