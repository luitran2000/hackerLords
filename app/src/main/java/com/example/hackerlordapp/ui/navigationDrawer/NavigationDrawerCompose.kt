package com.example.hackerlordapp.ui.navigationDrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hackerlordapp.R
import com.example.hackerlordapp.ui.theme.HackerLordAppTheme

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp) // Set the desired fixed height here
            .padding(vertical = 64.dp)
            .background(Color.LightGray)
    ) {
        // Load the PNG image and display it using the Image composable
        Image(
            painter = painterResource(id = R.drawable.main_icon), // Replace with the correct resource ID
            contentDescription = null,  // Provide a content description if needed
            modifier = Modifier.fillMaxSize() // Fill the available space
        )
    }
}


@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Set the Row width to fill the parent
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                Icon(imageVector = item.icon, contentDescription = item.contentDescriptor)
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = item.title,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.contentDescriptor,
                        style = TextStyle(color = Color.Gray)
                    )
                }
            }
        }
    }
}


// Example usage of DrawerBody:
val menuItems = listOf(

    MenuItem(
        id = "settings",
        title = "Settings",
        contentDescriptor = "Navigate to Settings",
        icon = Icons.Default.Settings
    ),
    MenuItem(
        id = "help",
        title = "Help",
        contentDescriptor = "Give info",
        icon = Icons.Default.Info
    ),
    MenuItem(
        id = "logout",
        title = "Logout",
        contentDescriptor = "Navigate to login",
        icon = Icons.Default.Lock
    )
)

@Preview(showBackground = true)
@Composable
fun Preview() {
    HackerLordAppTheme {
        DrawerHeader()

    }
}
