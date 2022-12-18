import {PostProps} from "../../interfaces/post"
import styles from "../../styles/Post.module.scss"


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
