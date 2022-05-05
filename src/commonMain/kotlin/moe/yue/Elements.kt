package moe.yue

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.yue.common.imagePainter
import moe.yue.common.openWebpage
import moe.yue.common.setClipboard
import moe.yue.data.userData

@Composable
fun AvatarCard(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Surface(
            modifier = Modifier.size(100.dp).align(Alignment.CenterHorizontally),
            shape = CircleShape,
            color = MaterialTheme.colors.surface,
            elevation = 8.dp
        ) {
            Image(painter = imagePainter(userData.avatarPath), "Avatar")
        }
        Text(
            userData.userName,
            modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
fun ContactCards(modifier: Modifier = Modifier) {
    CardsGrid(modifier = modifier) {
        userData.socialMedia.forEach {
            InfoCard(text = it.platformName, onClick = {
                it.url?.let { url -> openWebpage(url) }
                it.clipboard?.let { content -> setClipboard(content) }
            })
        }
    }
}
