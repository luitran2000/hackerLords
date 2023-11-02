package com.example.hackerlordapp.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.example.hackerlordapp.ui.appBar.TopBar
import com.example.hackerlordapp.ui.bottomBar.CustomBottomNavigation
import com.example.hackerlordapp.ui.bottomBar.CustomBottomNavigationItem
import com.example.hackerlordapp.ui.bottomBar.Screen
import com.example.hackerlordapp.ui.navigationDrawer.DrawerBody
import com.example.hackerlordapp.ui.navigationDrawer.DrawerHeader
import com.example.hackerlordapp.ui.navigationDrawer.MenuItem
import com.example.hackerlordapp.ui.theme.HackerLordAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    @SuppressLint(
        "UnusedMaterial3ScaffoldPaddingParameter",
        "UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackerLordAppTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                val currentScreen = mutableStateOf<Screen>(Screen.Home)
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
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "home",
                                    title = "Home",
                                    "navigate to home",
                                    icon = Icons.Default.Home
                                ),
                                MenuItem(
                                    id = "settings",
                                    title = "Settings",
                                    "navigate to Settings",
                                    icon = Icons.Default.Settings
                                ),
                                MenuItem(
                                    id = "help",
                                    title = "Help",
                                    "give info",
                                    icon = Icons.Default.Info
                                ),
                                MenuItem(
                                    id = "logout",
                                    title = "Logout",
                                    "navigate to login",
                                    icon = Icons.Default.Lock
                                ),
                            )
                        ) {
                            when(it.id){
                            //    "home" -> navigateUpTo(),
                            //    "settings" -> navigateUpTo(),
                            //    "help" -> navigateUpTo(),
                            //    "logout" -> navigateUpTo(),

                            }
                        }
                    },
                    bottomBar = {
                        CustomBottomNavigation(currentScreenId = currentScreen.value.id) {
                            currentScreen.value = it
                        }
                    }
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen() {
    val cameraPermissionState: PermissionState =
        rememberPermissionState(android.Manifest.permission.CAMERA)
    MainContent(
        hasPermission = cameraPermissionState.hasPermission,
        onRequestPermission = cameraPermissionState::launchPermissionRequest
    )
}

@Composable
private fun MainContent(
    hasPermission: Boolean,
    onRequestPermission: () -> Unit
) {
    if (hasPermission) {
        CameraScreen()
    } else {
        NoPermissionScreen(onRequestPermission)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    HackerLordAppTheme {
        CameraScreen()
    }
}