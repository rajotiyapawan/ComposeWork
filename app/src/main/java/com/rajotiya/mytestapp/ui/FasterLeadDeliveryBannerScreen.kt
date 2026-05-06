package com.rajotiya.mytestapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.rajotiya.mytestapp.R
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbGold
import com.rajotiya.mytestapp.aob_revamp.ui.theme.mbRed
import com.rajotiya.mytestapp.aob_revamp.ui.theme.textColorDark
import com.rajotiya.mytestapp.aob_revamp.utils.noRippleClick
import com.rajotiya.mytestapp.models.FasterLeadDeliveryBannerData
import com.rajotiya.mytestapp.models.ProjectItem
import com.rajotiya.mytestapp.utility.getFontFamily

/**
 * Composable screen for Faster Lead Delivery Banner
 * Displays promotional banner for Project Listings with projects grid
 *
 * @param data The banner data containing text and project list
 * @param onProjectClick Lambda called when a project button is clicked with project item
 * @param onShowMoreClick Lambda called when "Show More" button is clicked
 * @param modifier Modifier for the composable
 */
@Composable
fun FasterLeadDeliveryBannerScreen(
    data: FasterLeadDeliveryBannerData,
    onProjectClick: (ProjectItem) -> Unit = {},
    onShowMoreClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF1A1A1A), // Dark black
                        Color(0xFF2D1B2E), // Dark reddish-purple
                        Color(0xFF3D2840)  // Darker reddish-purple
                    )
                )
            )
            .padding(16.dp)
    ) {
        // Main content
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row {
                // Headlines section
                HeadlinesSection(
                    modifier = Modifier.weight(1f),
                    headlinePart1 = data.headlinePart1,
                    headlinePart2 = data.headlinePart2,
                    subHeadline = data.subHeadline
                )
                Spacer(Modifier.width(10.dp))
                // Visual elements section with magnet and avatars
                Image(
                    modifier = Modifier.size(128.dp),
                    painter = painterResource(R.drawable.ic_aob_coworking_unselected),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(4.dp))


            // Projects grid
            ProjectsGrid(
                projects = data.projects,
                onProjectClick = onProjectClick,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Show More button
            ShowMoreButton(
                text = data.showMoreText,
                onClick = onShowMoreClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun HeadlinesSection(
    modifier: Modifier,
    headlinePart1: String,
    headlinePart2: String,
    subHeadline: String
) {
    Column(
        modifier = modifier // Space for magnet on right
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xFFFFc72c))) {
                    append(headlinePart1)
                }
                append(headlinePart2)
            },
            fontSize = 16.sp,
            fontFamily = getFontFamily(weight = FontWeight.Bold),
            color = Color.White, // Yellow
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = subHeadline,
            fontSize = 14.sp,
            fontFamily = getFontFamily(),
            color = Color.White, // Light gray
            lineHeight = 18.sp
        )
    }
}

@Composable
private fun VisualElementsSection(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterEnd
    ) {
        // Concentric rings background
        Box(
            modifier = Modifier
                .size(200.dp)
                .offset(x = (-20).dp),
            contentAlignment = Alignment.Center
        ) {
            ConcentricRings()
        }

        // Container for magnet and avatars
        Box(
            modifier = Modifier
                .size(200.dp)
                .offset(x = (-20).dp),
            contentAlignment = Alignment.Center
        ) {
            // User avatars around the magnet
            UserAvatars()

            // Magnet icon centered
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .zIndex(2f),
                contentAlignment = Alignment.Center
            ) {
                MagnetIcon()
            }
        }
    }
}

@Composable
private fun ConcentricRings() {
    // Creating concentric rings effect
    repeat(3) { index ->
        Box(
            modifier = Modifier
                .size((200 - index * 40).dp)
                .border(
                    width = 1.dp,
                    color = Color(0x44FF0000).copy(alpha = 0.15f - index * 0.04f),
                    shape = CircleShape
                )
        )
    }
}

@Composable
private fun MagnetIcon() {
    // Creating a U-shaped magnet using Box
    Box(
        modifier = Modifier.size(100.dp, 80.dp)
    ) {
        // Top horizontal bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFFF3333), // Bright red
                            Color(0xFFCC0000), // Darker red
                            Color(0xFFFF3333)  // Bright red
                        )
                    ),
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
        )
        // Left vertical bar
        Box(
            modifier = Modifier
                .width(24.dp)
                .height(60.dp)
                .offset(x = 0.dp, y = 20.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFF3333),
                            Color(0xFFFF6666),
                            Color(0xFFFF4444)
                        )
                    ),
                    shape = RoundedCornerShape(bottomStart = 12.dp)
                )
        )
        // Right vertical bar
        Box(
            modifier = Modifier
                .width(24.dp)
                .height(60.dp)
                .offset(x = 76.dp, y = 20.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFF3333),
                            Color(0xFFFF6666),
                            Color(0xFFFF4444)
                        )
                    ),
                    shape = RoundedCornerShape(bottomEnd = 12.dp)
                )
        )
        // Adding silver/metallic highlight effect
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.4f),
                            Color.White.copy(alpha = 0.1f),
                            Color.White.copy(alpha = 0.4f)
                        )
                    ),
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
        )
    }
}

@Composable
private fun UserAvatars() {
    // Avatar 1 - Top left
    UserAvatar(
        modifier = Modifier
            .offset(x = (-50).dp, y = (-40).dp)
            .size(40.dp)
            .zIndex(1f),
        backgroundColor = Color(0xFF4A90E2)
    )
    // Avatar 2 - Top right
    UserAvatar(
        modifier = Modifier
            .offset(x = 50.dp, y = (-40).dp)
            .size(40.dp)
            .zIndex(1f),
        backgroundColor = Color(0xFF50C878)
    )
    // Avatar 3 - Bottom center
    UserAvatar(
        modifier = Modifier
            .offset(x = 0.dp, y = 60.dp)
            .size(40.dp)
            .zIndex(1f),
        backgroundColor = Color(0xFFE94B3C)
    )
}

@Composable
private fun UserAvatar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF4A90E2)
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .border(width = 2.dp, color = Color.White, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        // You can replace this with actual user images using Image composable
        Box(
            modifier = Modifier
                .fillMaxSize(0.8f)
                .clip(CircleShape)
                .background(Color(0xFF333333))
        )
    }
}

@Composable
private fun ProjectsGrid(
    projects: List<ProjectItem>,
    onProjectClick: (ProjectItem) -> Unit,
    gridSize: Int = 3,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // First row
        if (projects.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                projects.take(gridSize).forEach { project ->
                    ProjectButton(
                        project = project,
                        onClick = { onProjectClick(project) },
//                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Second row
        if (projects.size > gridSize) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                projects.drop(gridSize).take(gridSize).forEach { project ->
                    ProjectButton(
                        project = project,
                        onClick = { onProjectClick(project) },
//                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun ProjectButton(
    project: ProjectItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val textColor = if (project.isHighlighted) {
        mbRed
    } else {
        Color.Black
    }

    val borderColor = if (project.isHighlighted) {
        mbRed
    } else {
        Color(0xffd7d7d7)
    }

    Row(
        modifier = modifier
            .noRippleClick(onClick = onClick)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 5.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = project.name,
            fontSize = 10.sp, fontFamily = getFontFamily(weight = FontWeight.SemiBold),
            color = textColor,
            maxLines = 1,
//            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = textColor,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
private fun ShowMoreButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .noRippleClick(onClick = onClick),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 12.sp, lineHeight = 20.sp,
            fontFamily = getFontFamily(weight = FontWeight.SemiBold),
            color = Color(0xFFFFc72c), // Yellow
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowRight,
            contentDescription = null,
            tint = Color(0xFFFFc72c),
            modifier = Modifier.size(16.dp)
        )
    }
}


@Composable
fun FasterLeadDeliveryBannerPreview() {
    val sampleData = FasterLeadDeliveryBannerData(
        subHeadline = "Don't miss! Post them using Project Listings to get more leads, fast",
        projects = listOf(
            ProjectItem(id = "1", name = "Godrej Aristocrat", isHighlighted = false),
            ProjectItem(id = "2", name = "ATS One Hemlet", isHighlighted = true),
            ProjectItem(id = "3", name = "Supernova", isHighlighted = false),
            ProjectItem(id = "4", name = "Godrej Aristocrat", isHighlighted = false),
            ProjectItem(id = "5", name = "Supernova", isHighlighted = false),
            ProjectItem(id = "6", name = "ATS One Hemlet", isHighlighted = false)
        ),
        showMoreText = "Show More"
    )

    FasterLeadDeliveryBannerScreen(
        data = sampleData,
        onProjectClick = { project ->
            // Handle project click
            println("Project clicked: ${project.name}")
        },
        onShowMoreClick = {
            // Handle show more click
            println("Show more clicked")
        }
    )
}

@Composable
fun ProjectCardBottom(
    data: FasterLeadDeliveryBannerData,
    onProjectClick: (ProjectItem) -> Unit = {},
    onShowMoreClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Column(
        modifier
            .background(
                color = Color(0xffafeaef),
                shape = RoundedCornerShape(bottomStart = 11.dp, bottomEnd = 11.dp)
            )
            .padding(start = 16.dp, top = 12.dp, bottom = 17.dp, end = 20.dp)
    ) {
        Text(buildAnnotatedString {
            append("These ")
            withStyle(style = SpanStyle(color = mbGold)) {
                append("nearby Projects")
            }
            append(" have ")
            withStyle(style = SpanStyle(color = mbGold)) {
                append("Faster Lead Delivery!")
            }
            append(" Post now using your Project Listings")
        }, fontSize = 12.sp, color = Color.White, fontFamily = getFontFamily(weight = FontWeight.Bold))
        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.Bottom) {
            // Projects grid
            ProjectsGrid(
                projects = data.projects,
                onProjectClick = onProjectClick,
                gridSize = 2,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Show More button
            ShowMoreButton(
                text = data.showMoreText,
                onClick = onShowMoreClick,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun ManageProjectsDialog(modifier: Modifier = Modifier, data: FasterLeadDeliveryBannerData, onProjectClick: (ProjectItem) -> Unit) {
    Column(modifier) {
        Text(
            "Manage Projects",
            fontSize = 18.sp,
            lineHeight = 18.sp,
            color = textColorDark,
            fontFamily = getFontFamily(weight = FontWeight.Bold)
        )
        Spacer(Modifier.height(22.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 40.dp)
        ) {
            items(data.projects){ item->
                ProjectButton(item, onClick = {onProjectClick(item)}, modifier = Modifier.shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp)))
            }
        }
    }
}

