import styles from './Index.module.scss'
import socialCardStyles from './SocialCard.module.scss'

import userData from "@/app/_data/user-data"
import SocialCard from "./social-card"
import Avatar from "./avatar"
import React from "react"

export default function Index() {
    return (
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
    )
}
