import React from "react"
import styles from "../styles/Layout.module.scss"
import {footerText} from "../_data/layout"


const Footer = () => {
    return (
        <footer className={styles.footer}>
            <a>{footerText}</a>
        </footer>
    )
}

export default Footer
