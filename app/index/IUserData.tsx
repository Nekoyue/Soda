import {ISocialIcons} from "./ISocialIcons"
import {StaticImageData} from "next/image"

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
    description?: string,
    avatar: StaticImageData
}
