package moe.yue.data

import androidx.compose.ui.graphics.vector.ImageVector

val userData = UserData()



class UserData {
    data class SocialMedia(
        val platformName: String,
        val accountId: String? = null,
        val url: String? = null,
        val clipboard: String? = null,
        val icon: ImageVector? = null
    )

    val socialMedia = listOf(
        SocialMedia("Twitter", null, "https://twitter.com/NekoyueW"),
        SocialMedia("Telegram", null, "https://t.me/Nekoyue"),
        SocialMedia("GitHub", null, "https://github.com/Nekoyue"),
        SocialMedia("Instagram", null, "https://www.instagram.com/nekoyuew"),
        SocialMedia("osu!", null, "https://osu.ppy.sh/users/10079923"),
        SocialMedia(
            "PGP Key", null,
            "https://keys.openpgp.org/vks/v1/by-fingerprint/A43A9C1D771CAFAC6937B2F67F9A57293846C8AC"
        ),
        SocialMedia("Version 2022.5.5", null, "https://github.com/Nekoyue/Soda")
    )

    val userName = "Kagurazaka Tsuki"

    val avatarPath = "avatar.jpg"
}