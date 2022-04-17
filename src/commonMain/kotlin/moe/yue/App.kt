package moe.yue

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.yue.common.getPlatformName
import moe.yue.common.openWebpage
import moe.yue.common.setClipboard
import moe.yue.data.imagePainter
import moe.yue.data.userData

const val AppTitle = "Soda"

enum class WindowSizeClass { Mobile, Desktop }

//TODO: get rid of material components

@Composable
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    val platformName = getPlatformName()

    MaterialTheme(
        colors = LightColors
    ) {
        RootLayout()
    }
}

@Composable
fun RootLayout() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colors.surface.copy(0.75f),
            modifier = Modifier.padding(32.dp).fillMaxSize().align(Alignment.Center)
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                AvatarCard(Modifier.align(Alignment.CenterVertically).weight(1f))
                ContactCards(Modifier.align(Alignment.CenterVertically).weight(1f, false))
            }
        }
    }
}

@Composable
fun AvatarCard(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Surface(
            modifier = Modifier.size(100.dp).align(Alignment.CenterHorizontally),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface,
            elevation = 8.dp
        ) {
            Image(painter = imagePainter(userData.avatarPath), "Avatar")
        }
        Text(
            userData.userName,
            modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally)
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

