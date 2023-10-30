package com.example.hackerlordapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun NoPermissionScreen(
    onRequestPermission: ()-> Unit){
    NoPermissionContent(onRequestPermission = onRequestPermission)
}
@Composable
fun NoPermissionContent(
    onRequestPermission: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "Please grant the permission to use the camera to use the core functionality of this app.")
        Button(onClick = onRequestPermission){
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Camera")
            Text(text="Grant Permission")
        }
    }

}