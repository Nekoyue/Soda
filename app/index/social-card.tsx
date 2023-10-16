import {ISocialMedia} from "./IUserData"

import styles from './SocialCard.module.scss'

type Props = {
    socialMedia: ISocialMedia
}

const SocialCard = ({socialMedia}: Props) => {
    const icon = (
        <span className={styles.icon}>
                {socialMedia.icon?.svgIcon}
        </span>
    )

    return (
        <a className={styles.card}
           href={socialMedia.url}
           style={{backgroundColor: socialMedia.icon?.colorCode}}
           title={socialMedia.platformName}
        >
            {icon}
            <span className={styles.text}>{socialMedia.accountId}</span>

        </a>
    )
}

export default SocialCard
