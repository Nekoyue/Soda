import userData from "./user-data"
import {IHeader, IHyperlink} from "../IHeader"
import LogoImage from "./logo.png"

export const footerText = `CC BY-SA © ${new Date().getFullYear()} ${userData.userName}`

class Hyperlink implements IHyperlink {
    constructor(readonly text: string,
                readonly url: string) {
    }
}

class Header implements IHeader {
    logo = LogoImage
    description = "Soda"
    navigationItems = [
        new Hyperlink("Test", "/posts/test"),
        new Hyperlink("Google", "https://google.com")
    ]
}

export const headerData = new Header()

