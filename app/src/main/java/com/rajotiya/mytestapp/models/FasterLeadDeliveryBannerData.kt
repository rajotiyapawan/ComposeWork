package com.rajotiya.mytestapp.models

/**
 * Data model for Faster Lead Delivery Banner
 * Created for Project Listings promotion banner
 */
data class FasterLeadDeliveryBannerData(
    val headlinePart1: String = "These Projects ",
    val headlinePart2: String = "have Faster Lead Delivery with Project Listings",
    val subHeadline: String = "Don't miss! Post them using Project Listings to get more leads, fast",
    val projects: List<ProjectItem> = emptyList(),
    val showMoreText: String = "Show More"
)

/**
 * Data model for individual project item
 */
data class ProjectItem(
    val id: String,
    val name: String,
    val isHighlighted: Boolean = false // For projects that should be displayed with red background
)
