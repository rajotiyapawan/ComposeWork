package com.rajotiya.mytestapp.loyalty.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.rajotiya.mytestapp.R
import kotlin.math.roundToInt


@Composable
fun LoyalityVideoPlayer(
    modifier: Modifier = Modifier,
    isFullScreenView: Boolean = false,
    onCrossClick: () -> Unit
) {
    val context = LocalContext.current
    var isFullScreen by remember { mutableStateOf(isFullScreenView) }
    var backgroundColor by remember { mutableStateOf(Color.Transparent) }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem =
                MediaItem.fromUri("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")
            setMediaItem(mediaItem)
            repeatMode = Player.REPEAT_MODE_ALL
            prepare()
            playWhenReady = isFullScreenView
        }
    }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp // Convert screen width to pixels
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val videoPlayerWidth by animateDpAsState(
        targetValue = if (isFullScreen) (screenWidth - 32.dp) else 100.dp, // Adjust expanded and collapsed widths as needed
        animationSpec = tween(durationMillis = 300)
    )

    val startPx = with(LocalDensity.current) { 16.dp.toPx() }

    DisposableEffect(key1 = exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }

    var offsetX by remember { mutableStateOf(0f) } // X position
    var offsetY by remember { mutableStateOf(0f) } // Y position

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        contentAlignment = Alignment.CenterStart
    ) {
        // Video Player (draggable)
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        offsetX.roundToInt(),
                        offsetY.roundToInt()
                    )
                } // Apply the position offset
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, _, _ ->
                        if (!isFullScreen) {
                            offsetX = (offsetX + pan.x).coerceIn(
                                0f,
                                (screenWidth - videoPlayerWidth).toPx()
                            )
                            offsetY = (offsetY + pan.y).coerceIn(
                                -(screenHeight / 2 - videoPlayerWidth * 3 / 4).toPx(),
                                (screenHeight / 2 - videoPlayerWidth * 3 / 4).toPx()
                            )
                        }
                    }
                }
                .width(videoPlayerWidth)
                .aspectRatio(16 / 9f)
            // Placeholder for video (replace with your video player)
        ) {
            AndroidView(
                factory = { context ->
                    PlayerView(context).apply {
                        player = exoPlayer
                        useController = false // Hide default controls
                    }
                },
                modifier = Modifier.clip(RoundedCornerShape(24.dp))
            )
            CustomVideoControls(
                exoPlayer, isFullScreenView,
                onCrossClick = {
                    onCrossClick()
                }
            ) {
                isFullScreen = it
                if (isFullScreen) {
                    backgroundColor = Color(0x80000000)
                    offsetX = startPx
                    offsetY = startPx
                } else {
                    backgroundColor = Color.Transparent
                }
            }
        }
    }
}

@Composable
fun CustomVideoControls(
    exoPlayer: ExoPlayer,
    isFullScreenView: Boolean,
    onCrossClick: () -> Unit,
    onFullScreen: (Boolean) -> Unit
) {
    var isMuted by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(exoPlayer.isPlaying) }
    var isFullScreen by remember { mutableStateOf(false) }


    // Listen to ExoPlayer's playback state and update isPlaying
    DisposableEffect(exoPlayer) {
        val listener = object : Player.Listener {
            override fun onIsPlayingChanged(isPlayingState: Boolean) {
                isPlaying = isPlayingState
            }
        }
        exoPlayer.addListener(listener)
        onDispose {
            exoPlayer.removeListener(listener)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(16.dp),
    ) {

        // Play/Pause Button
//        IconButton(onClick = {
//            if (exoPlayer.isPlaying) {
//                exoPlayer.pause()
//            } else {
//                exoPlayer.play()
//            }
//        }) {
//            Icon(
//                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
//                contentDescription = "Play/Pause"
//            )
//        }

        if (!isFullScreenView) {
            IconButton(onClick = {
                isFullScreen = !isFullScreen
                if (exoPlayer.isPlaying) {
                    exoPlayer.pause()
                } else {
                    exoPlayer.play()
                }
                onFullScreen(isFullScreen)
            }) {
                Image(
                    painter = if (isFullScreen) painterResource(R.drawable.close_fullscreen_24) else painterResource(
                        R.drawable.open_in_full_24
                    ),
                    contentDescription = "expend/",
                )
            }
        }
        Spacer(Modifier.weight(1f))
        if (isFullScreen || isFullScreenView) {
            IconButton(onClick = {
                isMuted = !isMuted
                exoPlayer.volume = if (isMuted) 0f else 1f
            }) {
                Image(
                    painter = if (isMuted) painterResource(R.drawable.volume_off_24) else painterResource(
                        R.drawable.volume_up_24
                    ),
                    contentDescription = "Mute/Unmute",
                )
            }
        } else {
            IconButton(onClick = {
                onCrossClick()
            }) {
                Image(
                    painter = painterResource(R.drawable.share_prop_close_icon),
                    contentDescription = "Mute/Unmute",
                )
            }
        }
    }
}
