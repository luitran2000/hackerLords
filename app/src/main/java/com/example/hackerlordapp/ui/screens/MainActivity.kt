package com.example.hackerlordapp.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hackerlordapp.ui.appBar.TopBar
import com.example.hackerlordapp.ui.bottomBar.CustomBottomNavigation
import com.example.hackerlordapp.ui.bottomBar.Screen
import com.example.hackerlordapp.ui.components.RoundedButton
import com.example.hackerlordapp.ui.navigationDrawer.DrawerBody
import com.example.hackerlordapp.ui.navigationDrawer.DrawerHeader
import com.example.hackerlordapp.ui.navigationDrawer.menuItems
import com.example.hackerlordapp.ui.theme.HackerLordAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

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
                        DrawerBody(items = menuItems) { item ->
                            when (item.id) {
                                /*
                                "home" ->  navController.navigate("mainActivity")
                                "settings" -> navigateToSettings()
                                "help" -> navigateToHelp()
                                "logout" -> navigateToLogin()
                                 */
                            }
                        }
                    },
                    bottomBar = {
                        CustomBottomNavigation(currentScreenId = currentScreen.value.id) {
                            currentScreen.value = it
                        }
                    }
                ) {
                    Content()
                }
            }
        }
    }
}
@Composable
fun Content() {
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

        RoundedButton("Kamera", onClick = {})
    }
}
@Composable
fun SearchBar(
    text: String,
    onTextChanged: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White) // Set your desired background color
            .padding(8.dp),
        placeholder = { Text(text = "Search...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        singleLine = true
    )
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
        DrawerHeader()
    }
}