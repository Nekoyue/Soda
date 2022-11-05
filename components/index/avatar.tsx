import styles from '../../styles/Avatar.module.scss'
import Image from "next/image"

import AvatarImage from "../../public/avatar.jpg"

const Avatar = () => {
    return (
        <div className={styles.root}>
            <Image src={AvatarImage} alt={"Avatar"} className={styles.avatar} width={256}/>
        </div>
    )
}
export default Avatar
