import {StaticImageData} from "next/image"

export interface IHeader {
    logo?: StaticImageData,
    description?: string,
    navigationItems: Array<IHyperlink>
}

export interface IHyperlink {
    text: string,
    url: string
}
