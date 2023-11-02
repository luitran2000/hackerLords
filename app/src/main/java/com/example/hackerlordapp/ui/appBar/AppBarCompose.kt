package com.example.hackerlordapp.ui.appBar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable

@Composable
fun TopBar(
    onNavigationIconClick: () -> Unit
){
    TopAppBar(
        title = {
            Text(text ="HackerLordApp")
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = Icons.Default.Menu,
                    contentDescription = "Toggle drawer")
            }
        }
    )
}