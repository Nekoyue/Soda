import './globals.scss'
import type {Metadata} from 'next'
import {description, title} from "@/app/_data/metadata";
import React from "react";
import styles from "@/app/Layout.module.scss";
import Link from "next/link";
import {footerText, headerData} from "@/app/_data/layout-config";
import Image from "next/image";
import ThemeSwitcher from "@/app/theme-switcher";


export const metadata: Metadata = {
    title: title,
    description: description,
}

export default function RootLayout(
    {children}: { children: React.ReactNode }) {
    return (
        <html lang="en">
        <body>
        <Header/>
        <div className={styles.root_layout}>{children}</div>
        <Footer/>
        </body>
        </html>
    )
}

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


const Footer = () => {
    return (
        <footer className={styles.footer}>
            <a>{footerText}</a>
        </footer>
    )
}
