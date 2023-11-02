package com.example.hackerlordapp.ui.navigationDrawer

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackerlordapp.ui.bottomBar.CustomBottomNavigation
import com.example.hackerlordapp.ui.bottomBar.CustomBottomNavigationItem
import com.example.hackerlordapp.ui.bottomBar.Screen

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 64.dp)
    ) {
        Text(text = "Header", fontSize = 60.sp)
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier.Companion = Modifier,
    //itemTextStyle: TextStyle = androidx.compose.ui.text.TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                Icon(imageVector = item.icon, contentDescription = item.contentDescriptor)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.title
            )
        }
    }
}

@Composable
@Preview
fun Prev1(){

}

@ExperimentalAnimationApi
@Composable
@Preview
fun Prev2() {

}
