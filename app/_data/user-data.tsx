import {ISocialMedia, IUserData} from "@/app/index/IUserData"
import socialIcons from "./social-icons"
import {ISocialIcons} from "@/app/index/ISocialIcons"
import AvatarImage from "./avatar.jpg"

class SocialMedia implements ISocialMedia {
    platformName: string
    accountId?: string
    icon?: ISocialIcons
    url?: string
    clipboard?: string

    constructor(platformName: string,
                {accountId, icon = socialIcons[platformName], url, clipboard}: {
                    accountId?: string,
                    icon?: ISocialIcons,
                    url?: string, clipboard?: string
                }) {
        this.platformName = platformName
        this.accountId = accountId
        this.icon = icon
        this.url = url
        this.clipboard = clipboard
    }
}

class UserData implements IUserData {
    socialMedias = [
        new SocialMedia("Twitter", {accountId: "@NekoyueW", url: "https://twitter.com/NekoyueW"}),
        new SocialMedia("Telegram", {accountId: "@Nekoyue", url: "https://t.me/Nekoyue"}),
        new SocialMedia("Discord", {accountId: "@nyayue", url: "https://discord.com/users/369120521964748802"}),
        new SocialMedia("GitHub", {accountId: "@Nekoyue", url: "https://github.com/Nekoyue"}),
        new SocialMedia("Instagram", {accountId: "@nekoyuew", url: "https://www.instagram.com/nekoyuew"}),
        new SocialMedia("osu!", {accountId: "@Nekoyue", url: "https://osu.ppy.sh/users/10079923"}),
        new SocialMedia("PGP Key", {
            accountId: "0x7F9A57293846C8AC",
            icon: socialIcons["Yubico"],
            url: "https://keys.openpgp.org/vks/v1/by-fingerprint/A43A9C1D771CAFAC6937B2F67F9A57293846C8AC"
        }),
        new SocialMedia("Version", {
            accountId: "ver. 2022.11.30",
            icon: socialIcons["Git"],
            url: "https://github.com/Nekoyue/Soda"
        })  // TODO: pull the version automatically
    ]

    userName = "Kagurazaka Tsuki"
    description = "cybernetic kawaii fiber-optic cat meow"

    avatar = AvatarImage
}

let userData = new UserData()
export default userData
