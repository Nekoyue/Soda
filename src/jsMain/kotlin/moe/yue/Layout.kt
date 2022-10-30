package moe.yue

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DesktopLayout() {
    RootLayout {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AvatarCard(Modifier.weight(1f))
            ContactCards(Modifier.weight(1f, false))
        }
    }
}


@Composable
fun MobileLayout() {
    RootLayout {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AvatarCard(Modifier)
            ContactCards(Modifier, centerAlignment = true)
        }
    }
}

@Composable
fun RootLayout(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        BoxWithConstraints {
            val defaultPadding = 32.dp

            val minHorizontalPadding = 400.dp
            val maxHorizontalPadding = 1000.dp
            val horizontalPadding =
                if (maxWidth < minHorizontalPadding) 0.dp
                else if (maxWidth > maxHorizontalPadding) (maxWidth - maxHorizontalPadding) / 2 + defaultPadding
                else defaultPadding

            val maxVerticalPadding = 800.dp
            val verticalPadding =
                if (horizontalPadding == 0.dp) 0.dp
                else if (maxHeight > maxVerticalPadding) (maxHeight - maxVerticalPadding) / 2 + defaultPadding
                else defaultPadding

//            Text("$maxWidth, $maxHeight") // debug overlay
            RootPanel(
                Modifier.padding(horizontal = horizontalPadding, vertical = verticalPadding)
                    .align(Alignment.Center)
            ) { content() }
        }

    }
}

@Composable
fun RootPanel(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colors.surface.copy(0.75f),
        modifier = modifier.fillMaxSize()
    ) {
        content()
    }
}