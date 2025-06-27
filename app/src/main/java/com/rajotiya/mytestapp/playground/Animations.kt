package com.rajotiya.mytestapp.playground

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * Created by Pawan Rajotiya on 27-06-2025.
 */

@Composable
fun AnimationsTests(modifier: Modifier = Modifier) {
    LazyColumn(modifier=modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // basic animations
        item { ColorAnimation() }
        // visibility animations
        item { VisibilityAnimation() }
        // transition animations
        item { MultipleAnimations() }
        // drag animations
        item { DraggableBox() }
        // infinite animations
        item { PulsingHeart() }
    }
}

@Composable
private fun ColorAnimation() {
    var isRed by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (isRed) Color.Red else Color.Blue,
        animationSpec = tween(durationMillis = 500)
    )

    Button(
        onClick = { isRed = !isRed },
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text("Toggle Color")
    }
}

@Composable
private fun VisibilityAnimation() {
    var visible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Text("Hello Animations!", modifier = Modifier.padding(16.dp))
    }

    Button(onClick = { visible = !visible }) {
        Text("Toggle Visibility")
    }
}

@Composable
private fun MultipleAnimations() {
    var isExpanded by remember { mutableStateOf(false) }
    val transition = updateTransition(isExpanded, label = "expandTransition")

    val borderWidth by transition.animateDp(label = "border") { expanded ->
        if (expanded) 4.dp else 1.dp
    }
    val padding by transition.animateDp(label = "padding") { expanded ->
        if (expanded) 32.dp else 8.dp
    }

    Box(
        modifier = Modifier
            .padding(padding)
            .border(borderWidth, Color.Blue)
            .clickable { isExpanded = !isExpanded }
    ) {
        Text("Tap me")
    }
}

@Composable
private fun DraggableBox() {
    val coroutineScope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
                .background(Color.Blue)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        coroutineScope.launch {
                            offsetX.snapTo(offsetX.value + dragAmount.x)
                            offsetY.snapTo(offsetY.value + dragAmount.y)
                        }
                    }
                }
        )
    }
}

@Composable
private fun PulsingHeart() {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = "Pulsing heart",
        modifier = Modifier
            .size(64.dp)
            .graphicsLayer { scaleX = scale; scaleY = scale },
        tint = Color.Red
    )
}