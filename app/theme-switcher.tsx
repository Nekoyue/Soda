'use client'

import styles from "./Layout.module.scss"
import ThemeSwitcherIcon from "./theme-switcher.svg"
import {useEffect} from "react"


const ThemeSwitcher = () => {
    // fetch & set default on page load
    useEffect(() => setTheme(getTheme()))

    return <button title={"Switch theme"} className={styles.header_right_theme_switcher} onClick={onClick}>
        <ThemeSwitcherIcon/>
    </button>
}

export default ThemeSwitcher

function onClick() {
    const currentTheme = getTheme()

    if (currentTheme == "dark")
        setTheme("light")
    else if (currentTheme == "light")
        setTheme("dark")
}

function getTheme(): string {
    return window.localStorage.getItem("theme") ||
        // use browser preference on first visit
        (window.matchMedia('(prefers-color-scheme: dark)').matches
            ? "dark" : "light")
}

function setTheme(theme: string) {
    document.documentElement.classList.remove("light-theme")
    document.documentElement.classList.remove("dark-theme")

    document.documentElement.classList.add(`${theme}-theme`)
    window.localStorage.setItem("theme", theme)
}

