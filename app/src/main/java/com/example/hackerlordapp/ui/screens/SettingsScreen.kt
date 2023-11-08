package com.example.hackerlordapp.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.example.hackerlordapp.ui.appBar.TopBar
import com.example.hackerlordapp.ui.bottomBar.CustomBottomNavigation
import com.example.hackerlordapp.ui.bottomBar.Screen
import com.example.hackerlordapp.ui.navigationDrawer.DrawerBody
import com.example.hackerlordapp.ui.navigationDrawer.DrawerHeader
import com.example.hackerlordapp.ui.navigationDrawer.menuItems
import com.example.hackerlordapp.ui.theme.HackerLordAppTheme
import kotlinx.coroutines.launch

class SettingsScreen : ComponentActivity() {

    @SuppressLint(
        "UnusedMaterial3ScaffoldPaddingParameter",
        "UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackerLordAppTheme {

            }
        }
    }
}


@SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingContent() {
    Text(text = "Settings Content")
}

