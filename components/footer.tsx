import React from "react"
import styles from "../styles/Layout.module.scss"
import userData from "../_data/user-data"


const Footer = () => {
    return (
        <footer className={styles.footer}>
            <a>CC BY-SA © {new Date().getFullYear()} {userData.userName}</a>
        </footer>
    )
}

export default Footer
