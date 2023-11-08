package com.example.hackerlordapp.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hackerlordapp.ui.appBar.TopBar
import com.example.hackerlordapp.ui.bottomBar.CustomBottomNavigation
import com.example.hackerlordapp.ui.bottomBar.Screen
import com.example.hackerlordapp.ui.components.RoundedButton
import com.example.hackerlordapp.ui.components.SearchBar
import com.example.hackerlordapp.ui.navigationDrawer.DrawerBody
import com.example.hackerlordapp.ui.navigationDrawer.DrawerHeader
import com.example.hackerlordapp.ui.navigationDrawer.menuItems
import com.example.hackerlordapp.ui.theme.HackerLordAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch

class MainScreen : ComponentActivity() {

    @SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackerLordAppTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                val currentScreen = mutableStateOf<Screen>(Screen.Home)
                val navController = rememberNavController()

                androidx.compose.material.Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopBar(onNavigationIconClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
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
                    bottomBar = {
                        CustomBottomNavigation(currentScreenId = currentScreen.value.id) { screen ->
                            when(screen) {
                                is Screen.Home -> navController.navigate("mainScreen")
                                is Screen.Search -> navController.navigate("searchScreen")
                                is Screen.Profile -> navController.navigate("profileScreen")
                                // Add other screens as needed
                                else -> {}
                            }
                        }
                    }

                ) {
                    NavHost(navController = navController, startDestination = "mainScreen") {
                        composable("mainScreen") {
                            MainContent(navController)
                        }
                        composable("cameraScreen") {
                            CheckPermission()
                        }
                        composable("settingScreen") {
                            SettingContent()
                        }
                        composable("helpScreen") {
                            HelpContent()
                        }
                        composable("logoutScreen") {
                            // Content for the logout screen
                            // Replace with your actual logout screen content
                        }
                        composable("searchScreen") {
                            // Content for the logout screen
                            // Replace with your actual logout screen content
                        }
                        composable("profileScreen") {
                            // Content for the logout screen
                            // Replace with your actual logout screen content
                        }
                    }
                }
            }
        }
    }


    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun MainContent(navController: NavController) {
        val cameraPermissionState: PermissionState =
            rememberPermissionState(android.Manifest.permission.CAMERA)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                text = "", // Provide your text state here
                onTextChanged = { /* Update your text state here */ }
            )
            Spacer(modifier = Modifier.height(16.dp))

            RoundedButton("Kamera", onClick = {
                if (!cameraPermissionState.hasPermission) {
                    // Request the camera permission
                    cameraPermissionState.launchPermissionRequest()
                }else{
                    navController.navigate("cameraScreen")
                }
            })
        }
    }

}

