import {ISocialIcons} from "../interfaces/social-icons"

import Twitter from "../public/social-icons/twitter.svg"
import Telegram from "../public/social-icons/telegram.svg"
import Discord from "../public/social-icons/discord.svg"
import Github from "../public/social-icons/github.svg"
import Instagram from "../public/social-icons/instagram.svg"
import Osu from "../public/social-icons/osu.svg"
import Yubico from "../public/social-icons/yubico.svg"
import Git from "../public/social-icons/git.svg"

class SocialIcons implements ISocialIcons {
    constructor(readonly colorCode: string, readonly svgIcon: JSX.Element) {
    }
}

// svg files should be placed at ../public/social-icons/*
let socialIcons: Record<string, SocialIcons> = {
    "Twitter": new SocialIcons("#1DA1F2", <Twitter/>),
    "Telegram": new SocialIcons("#26A5E4", <Telegram/>),
    "Discord": new SocialIcons("#5865F2", <Discord/>),
    "GitHub": new SocialIcons("#24292f", <Github/>),
    "Instagram": new SocialIcons("#E4405F", <Instagram/>),
    "osu!": new SocialIcons("#FF66AA", <Osu/>),
    "Yubico": new SocialIcons("#84BD00", <Yubico/>),
    "Git": new SocialIcons("#F05032", <Git/>)
}

export default socialIcons
