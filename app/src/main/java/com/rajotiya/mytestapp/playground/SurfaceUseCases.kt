package com.rajotiya.mytestapp.playground

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * Created by Pawan Rajotiya on 26-06-2025.
 */

@Composable
fun SurfaceUseCases(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier.background(color = Color.Cyan),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { AnimatedElevationSurface() }
        item { DrawingSurface() }
        item { SurfaceWithPager() }
        item { GradientSurface() }
        item { DraggableStarSurface() }
        item { ThemedSurfaceWithToggle() }
    }

}

@Composable
private fun AnimatedElevationSurface() {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val elevation = animateDpAsState(targetValue = if (isPressed) 0.dp else 8.dp)

    Surface(
        modifier = Modifier
            .padding(16.dp)
            .clickable(interactionSource = interactionSource, indication = null) {},
        shadowElevation = elevation.value,
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.onPrimary
    ) {
        Text(
            "Press Me",
            modifier = Modifier.padding(16.dp),
            color = Color.White
        )
    }
}

@Composable
private fun DrawingSurface() {
    val path = remember { Path() }
    val currentPath = remember { mutableStateOf(path) }
    val paths = remember { mutableStateListOf<Path>() }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { touch ->
                        path.moveTo(touch.x, touch.y)
                    },
                    onDrag = { change, _ ->
                        path.lineTo(change.position.x, change.position.y)
                        currentPath.value = path
                    },
                    onDragEnd = {
                        paths.add(path)
                    }
                )
            },
        color = Color.White,
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            paths.forEach { drawPath(it, Color.Black, style = Stroke(4f)) }
            drawPath(currentPath.value, Color.Black, style = Stroke(4f))
        }
    }
}

@Composable
fun SurfaceWithPager() {
    val pagerState = rememberPagerState { 3 }
    val pages = listOf("Page 1", "Page 2", "Page 3")

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        HorizontalPager(
//            count = pages.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        when (page) {
                            0 -> Color(0xFFFF7676)
                            1 -> Color(0xFF76FF8B)
                            else -> Color(0xFF768AFF)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    pages[page],
//                    style = MaterialTheme.typography.h3,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun GradientSurface() {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(3000))
    )

    Surface(
        modifier = Modifier
            .padding(16.dp)
            .height(200.dp),
        shape = RoundedCornerShape(24.dp),
        shadowElevation = 12.dp
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.sweepGradient(
                        colors = listOf(
                            Color(0xFF833AB4),
                            Color(0xFFFD1D1D),
                            Color(0xFFFCB045)
                        ),
                        center = Offset.Unspecified,
//                        angle = angle.deg
                    )
                )
                .padding(4.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Text(
                "Premium Content",
                modifier = Modifier.align(Alignment.Center),
//                style = MaterialTheme.typography.h4
            )
        }
    }
}

@Composable
fun DraggableStarSurface() {
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }

    Surface(
        modifier = Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                }
            },
        shape = StarShape(5), // Custom shape (see below)
        color = Color.Yellow,
        shadowElevation = 16.dp
    ) {
        Box(Modifier.size(100.dp))
    }
}

// Custom star shape
class StarShape(private val points: Int) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density) =
        Outline.Generic(Path().apply {
            val radius = size.minDimension / 2f
            val center = size.center
            var angle = -Math.PI / 2
            val angleStep = Math.PI * 2 / points

            moveTo(
                x = center.x + (radius * cos(angle)).toFloat(),
                y = center.y + (radius * sin(angle)).toFloat()
            )

            repeat(points) {
                angle += angleStep / 2
                lineTo(
                    x = center.x + (radius * 0.5f * cos(angle)).toFloat(),
                    y = center.y + (radius * 0.5f * sin(angle)).toFloat()
                )
                angle += angleStep / 2
                lineTo(
                    x = center.x + (radius * cos(angle)).toFloat(),
                    y = center.y + (radius * sin(angle)).toFloat()
                )
            }
            close()
        })
}

@Composable
fun ThemedSurfaceWithToggle() {
    var isDark by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        targetValue = if (isDark) Color.DarkGray else Color.White
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { isDark = !isDark },
        color = backgroundColor,
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                if (isDark) "Dark Mode" else "Light Mode",
                color = if (isDark) Color.White else Color.Black
            )
            Spacer(Modifier.height(8.dp))
            Icon(
                if (isDark) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Theme"
            )
        }
    }
}