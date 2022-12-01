import Head from 'next/head'
import styles from '../styles/Index.module.scss'
import socialCardStyles from '../styles/SocialCard.module.scss'

import userData from "../_data/user-data"
import SocialCard from "../components/index/social-card"
import Avatar from "../components/index/avatar"
import {description, title} from "../_data/metadata"
import Container from "../components/container"
import Footer from "../components/footer"

export default function Index() {
    return (
        <Container>
            <Head>
                <title>{title}</title>
                <meta name="description" content={description}/>
                <link rel="icon" href="/favicon.ico"/>
            </Head>

            <main className={styles.main}>
                <Avatar/>

                <h1 className={styles.title}>{userData.userName}</h1>


                <div className={socialCardStyles.root}>
                    {userData.socialMedias.map((it) =>
                        <SocialCard key={it.platformName} socialMedia={it}/>)}
                </div>


            </main>

            <Footer/>
        </Container>
    )
}
