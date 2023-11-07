package com.example.hackerlordapp.ui.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RoundedButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .defaultMinSize(minHeight = 64.dp, minWidth = 64.dp), // Adjust size as needed
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            disabledContentColor = Color.Blue, // Change the background color as needed
            contentColor = Color.White
        )
    ) {
        // Text inside the button
        Text(text = text)
    }
}
