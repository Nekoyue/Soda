import userData from "./user-data"
import {IHeader, IHyperlink} from "../interfaces/header"
import LogoImage from "../public/logo.png"


export let footerText = `CC BY-SA © ${new Date().getFullYear()} ${userData.userName}`

class Hyperlink implements IHyperlink {
    text: string
    url: string

    constructor(text: string, url: string) {
        this.text = text
        this.url = url
    }
}

class Header implements IHeader {
    logo = LogoImage
    description = "Soda"
    navigationItems = [
        new Hyperlink("About", "/about"),
        new Hyperlink("Google", "https://google.com")
    ]

}

export let headerData = new Header()

