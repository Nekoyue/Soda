import {ISocialIcons} from "./social-icons"

export interface ISocialMedia {
    platformName: string,
    accountId?: string,
    icon?: ISocialIcons,
    url?: string,
    clipboard?: string
}

export interface IUserData {
    socialMedias: Array<ISocialMedia>,
    userName: string,
    avatar: JSX.Element
}
