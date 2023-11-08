package com.example.hackerlordapp.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hackerlordapp.ui.appBar.SettingTopBar
import com.example.hackerlordapp.ui.navigationDrawer.DrawerBody
import com.example.hackerlordapp.ui.navigationDrawer.DrawerHeader
import com.example.hackerlordapp.ui.navigationDrawer.menuItems
import com.example.hackerlordapp.ui.theme.HackerLordAppTheme

class CameraActivity : AppCompatActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackerLordAppTheme {
                val scaffoldState = rememberScaffoldState()
                val navController = rememberNavController()

                androidx.compose.material.Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        SettingTopBar(onNavigationIconClick = {
                            navController.navigate("mainScreen")
                        })
                    },
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(items = menuItems) { item ->
                            when (item.id) {
                                "settings" -> navController.navigate("settingScreen")
                                "help" -> navController.navigate("helpScreen")
                                "logout" -> navController.navigate("logoutScreen")
                            }
                        }
                    },
                ) {

                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun Preview() {
    /*
    HackerLordAppTheme {
        val scaffoldState = rememberScaffoldState()
        val navController = rememberNavController()

        androidx.compose.material.Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                SettingTopBar(onNavigationIconClick = {
                    NavigateToMain(navController)
                })
            },
            drawerContent = {
                DrawerHeader()
                DrawerBody(items = menuItems) { item ->
                    when (item.id) {
                        "settings" -> navController.navigate("settingScreen")
                        "help" -> navController.navigate("helpScreen")
                        "logout" -> navController.navigate("logoutScreen")
                    }
                }
            },
        ) {
            CameraContent()
            NavHost(navController = navController, startDestination = "cameraScreen") {
                composable("mainScreen") {
                    MainContent()
                }
                composable("settingScreen") {
                    // Content for the setting screen
                    // Replace with your actual setting screen content
                }
                composable("helpScreen") {
                    // Content for the help screen
                    // Replace with your actual help screen content
                }
                composable("cameraScreen") {
                    // Content for the logout screen
                    // Replace with your actual logout screen content
                }
            }
        }
    }

     */
}



