import styles from './Avatar.module.scss'
import Image from "next/image"
import userData from "@/app/_data/user-data"


const Avatar = () => {
    return (
        <div className={styles.root}>
            <Image src={userData.avatar} alt={"Avatar"}
                   className={styles.avatar}
                   width={256}
                   priority={true} // disable lazy loading
            />
        </div>
    )
}
export default Avatar
