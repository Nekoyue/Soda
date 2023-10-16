import {ISocialIcons} from "@/app/index/ISocialIcons"

import Twitter from "@/app/index/social-icons/twitter.svg"
import Telegram from "@/app/index/social-icons/telegram.svg"
import Discord from "@/app/index/social-icons/discord.svg"
import Github from "@/app/index/social-icons/github.svg"
import Instagram from "@/app/index/social-icons/instagram.svg"
import Osu from "@/app/index/social-icons/osu.svg"
import Yubico from "@/app/index/social-icons/yubico.svg"
import Git from "@/app/index/social-icons/git.svg"

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
    "Git": new SocialIcons("#F05032", <Git/>),
}

export default socialIcons
