import React from "react"
import styles from '../styles/Layout.module.scss'
import Footer from "./footer"
import Header from "./header"
import Head from "next/head"
import {description, title} from "../_data/metadata"

type Props = {
    children?: React.ReactNode
}

const Container = ({children}: Props) => {
    return <>
        <Head>
            <title key="title">{title}</title>
            <meta name="description" content={description} key="description"/>
            <link rel="icon" href="/favicon.ico"/>
        </Head>
        <Header/>
        <div className={styles.container}>{children}</div>
        <Footer/>
    </>
}

export default Container
