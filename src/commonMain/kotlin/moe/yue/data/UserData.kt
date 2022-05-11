package moe.yue.data

val userData = UserData()


class UserData {
    data class SocialMedia(
        val platformName: String,
        val accountId: String? = null,
        val icon: SocialIcons? = null,
        val url: String? = null,
        val clipboard: String? = null,
    )

    val socialMedia = listOf(
        SocialMedia("Twitter", "@NekoyueW", socialIcons["Twitter"], "https://twitter.com/NekoyueW"),
        SocialMedia("Telegram", "@KagurazakaTsuki", socialIcons["Telegram"], "https://t.me/KagurazakaTsuki"),
        SocialMedia("Discord", "yue#4816", socialIcons["Discord"], clipboard = "yue#4816"),
        SocialMedia("GitHub", "@Nekoyue", socialIcons["GitHub"], "https://github.com/Nekoyue"),
        SocialMedia("Instagram", "@nekoyuew", socialIcons["Instagram"], "https://www.instagram.com/nekoyuew"),
        SocialMedia("osu!", "@Nekoyue", socialIcons["osu!"], "https://osu.ppy.sh/users/10079923"),
        SocialMedia(
            "PGP Key", "0x7F9A57293846C8AC", socialIcons["Yubico"],
            "https://keys.openpgp.org/vks/v1/by-fingerprint/A43A9C1D771CAFAC6937B2F67F9A57293846C8AC"
        ),
        SocialMedia("Version 2022.5.10", null, socialIcons["Git"], "https://github.com/Nekoyue/Soda")
    )

    val userName = "Kagurazaka Tsuki"

    val avatarPath = "avatar.jpg"
}