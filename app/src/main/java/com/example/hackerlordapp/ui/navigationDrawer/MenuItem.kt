package com.example.hackerlordapp.ui.navigationDrawer

import androidx.compose.ui.graphics.vector.ImageVector
import java.lang.invoke.TypeDescriptor


data class MenuItem(
    val id: String,
    val title: String,
    val contentDescriptor: String,
    val icon: ImageVector
)
