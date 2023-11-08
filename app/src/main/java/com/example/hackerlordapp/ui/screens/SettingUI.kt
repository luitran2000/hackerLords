package com.example.hackerlordapp.ui.screens

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("NotConstructor", "UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun SettingContent() {
        var switchState by remember { mutableStateOf(true) }
        var textValue by remember { mutableStateOf("Sample Text") }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Settings") }
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    Text("General Settings", style = MaterialTheme.typography.headlineLarge)
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Switch Setting")
                        Switch(
                            checked = switchState,
                            onCheckedChange = { newState -> switchState = newState }
                        )
                    }
                }

                item {
                    Text("Text Setting", style = MaterialTheme.typography.headlineLarge)
                }

                item {
                    BasicTextField(
                        value = textValue,
                        onValueChange = { textValue = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Button(
                        onClick = {
                            // Handle button click here
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(8.dp)
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun SettingsScreenPreview() {
        SettingContent()
    }



