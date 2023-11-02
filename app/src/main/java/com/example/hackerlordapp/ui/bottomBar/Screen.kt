package com.example.hackerlordapp.ui.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val id: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : Screen("home", "Home", Icons.Outlined.Home)
    object Search : Screen("seach", "Search", Icons.Outlined.Search)
    object Camera : Screen("camera", "Camera", Icons.Outlined.Search)
    object Profile : Screen("profile", "Profile", Icons.Outlined.AccountBox)
    object Items {
        val list = listOf(
            Home, Search, Camera, Profile
        )
    }
}