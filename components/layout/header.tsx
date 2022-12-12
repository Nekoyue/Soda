import React from "react"
import styles from "../../styles/Layout.module.scss"
import {headerData} from "../../_data/layout"
import Link from "next/link"
import Image from "next/image"
import ThemeSwitcher from "./theme-switcher"


const Header = () => {
    return (
        <header className={styles.header}>

            <Link className={styles.header_left} href="/">
                {headerData.logo &&
                    <Image src={headerData.logo} alt="Logo"
                           className={styles.header_left_logo}
                           width={28}
                    />}
                {headerData.description &&
                    <span className={styles.header_left_description}>{headerData.description}</span>}
            </Link>

            <div className={styles.header_divider}/>

            <nav className={styles.header_right}>{headerData.navigationItems.map((it) =>
                <Link key={it.text}
                      className={styles.header_right_item}
                      href={it.url}>
                    {it.text}
                </Link>
            )}</nav>
            <ThemeSwitcher/>
        </header>
    )
}

export default Header
