import styles from '../styles/Index.module.scss'
import socialCardStyles from '../styles/SocialCard.module.scss'

import userData from "../_data/user-data"
import SocialCard from "../components/index/social-card"
import Avatar from "../components/index/avatar"
import Container from "../components/container"
import React from "react"

export default function Index() {
    return (
        <Container>
            <main className={styles.main}>
                <Avatar/>

                <h1 className={styles.title}>{userData.userName}</h1>

                {userData.description &&
                    <h1 className={styles.description}>{userData.description}</h1>
                }


                <div className={socialCardStyles.root}>
                    {userData.socialMedias.map((it) =>
                        <SocialCard key={it.platformName} socialMedia={it}/>)}
                </div>
            </main>
        </Container>
    )
}
