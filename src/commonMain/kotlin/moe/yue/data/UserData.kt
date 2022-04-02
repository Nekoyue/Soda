package moe.yue.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

val userData = UserData()

@Composable
expect fun imagePainter(resourcePath: String): Painter

class UserData {
    data class SocialMedia(
        val platformName: String,
        val accountId: String? = null,
        val url: String,
        val icon: ImageVector? = null
    )

    val socialMedia = listOf(
        SocialMedia("Twitter", null, "https://twitter.com/NekoyueW"),
        SocialMedia("Telegram", null, "https://t.me/Nekoyue"),
        SocialMedia("GitHub", null, "https://github.com/Nekoyue"),
        SocialMedia("Instagram", null, "https://www.instagram.com/nekoyuew"),
        SocialMedia("osu!", null, "https://osu.ppy.sh/users/10079923"),
    )

    val userName = "Kagurazaka Tsuki"

    val avatarPath = "avatar.jpg"
}