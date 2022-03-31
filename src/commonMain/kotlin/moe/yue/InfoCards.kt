package moe.yue

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp

@Composable
fun CardsGrid(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // row counter
        var rows = 1

        // Keep track of the width & max height of each row
        // First row started with index 1
        val rowWidths = mutableListOf(0, 0)
        val rowHeights = mutableListOf(0, 0)

        // Map<row, List<placeable>>
        val placeablesMap = measurables.map { measurable ->
            val placeable = measurable.measure(constraints)

            if (rowWidths[rows] + placeable.width > constraints.maxWidth)
                rows += 1

            rowWidths.getOrElse(rows) { rowWidths.add(0) }
            rowHeights.getOrElse(rows) { rowHeights.add(0) }

            rowWidths[rows] += placeable.width
            rowHeights[rows] = maxOf(rowHeights[rows], placeable.height)
            Pair(rows, placeable)
        }   // List<Pair<row, placeable>> needs to be converted
            .groupBy { it.first }.mapValues { it.value.map { it.second } }


        // Grid's width and height
        val width = rowWidths.maxOrNull()
            ?.coerceIn(constraints.minWidth..constraints.maxWidth) ?: constraints.minWidth
        val height = rowHeights.sumOf { it }
            .coerceIn(constraints.minHeight..constraints.maxHeight)

        // x cord we have placed up to, per row
        val rowX = MutableList(rows + 1) { 0 }
        // Y of each row, based on the height accumulation of previous rows
        val rowY = MutableList(rows + 1) { 0 }
        for (row in 1..rows)
            rowY[row] = rowY[row - 1] + rowHeights[row - 1]


        layout(width, height) {
            placeablesMap.forEach { (row, placeables) ->
                placeables.forEach { placeable ->
                    placeable.placeRelative(
                        x = rowX[row],
                        y = rowY[row]
                    )
                    rowX[row] += placeable.width
                }
            }
        }
    }
}

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    text: String = "AAA",
    onClick: (() -> Unit)? = null,
) {
    Surface(
        modifier = modifier
            .height(45.dp)
            .width(100.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = { onClick?.invoke() }),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
    ) {
        Row {
            Text(text, modifier = Modifier.padding(5.dp))
        }
    }
}