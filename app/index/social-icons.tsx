import {ISocialIcons} from "@/app/index/ISocialIcons"
import type {JSX} from 'react'

import Bluesky from "@/app/index/social-icons/bluesky.svg"
import Discord from "@/app/index/social-icons/discord.svg"
import Git from "@/app/index/social-icons/git.svg"
import Github from "@/app/index/social-icons/github.svg"
import Instagram from "@/app/index/social-icons/instagram.svg"
import Osu from "@/app/index/social-icons/osu.svg"
import Telegram from "@/app/index/social-icons/telegram.svg"
import Twitter from "@/app/index/social-icons/twitter.svg"
import X from "@/app/index/social-icons/x.svg"
import Yubico from "@/app/index/social-icons/yubico.svg"

class SocialIcons implements ISocialIcons {
    constructor(readonly colorCode: string, readonly svgIcon: JSX.Element) {
    }
}

// svg files should be placed at ../public/social-icons/*
const socialIcons: Record<string, SocialIcons> = {
    "Bluesky": new SocialIcons("#0285FF", <Bluesky/>),
    "Discord": new SocialIcons("#5865F2", <Discord/>),
    "Git": new SocialIcons("#F05032", <Git/>),
    "GitHub": new SocialIcons("#24292f", <Github/>),
    "Instagram": new SocialIcons("#E4405F", <Instagram/>),
    "osu!": new SocialIcons("#FF66AA", <Osu/>),
    "Telegram": new SocialIcons("#26A5E4", <Telegram/>),
    "Twitter": new SocialIcons("#1DA1F2", <Twitter/>),
    "X": new SocialIcons("#000000", <X/>),
    "Yubico": new SocialIcons("#84BD00", <Yubico/>),
}

export default socialIcons
