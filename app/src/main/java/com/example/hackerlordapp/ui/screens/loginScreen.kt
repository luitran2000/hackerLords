package com.example.hackerlordapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.hackerlordapp.ui.theme.HackerLordAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isButtonEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF2196F3), // Custom text color
            modifier = Modifier.padding(16.dp)
        )

        TextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { /* Handle 'Next' action */ }
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text("Username")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (isButtonEnabled) {
                        performLogin(username, password)
                    }
                }
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text("Password")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (isButtonEnabled) {
                    performLogin(username, password)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = isButtonEnabled,
            colors = ButtonDefaults.buttonColors( Color(0xFF2196F3)) // Custom button color
        ) {
            Text("Login", color = Color.White)
        }
    }

    DisposableEffect(Unit) {
        val usernameNotEmpty = username.isNotEmpty()
        val passwordNotEmpty = password.isNotEmpty()
        isButtonEnabled = usernameNotEmpty && passwordNotEmpty

        onDispose { }
    }
}

private fun performLogin(username: String, password: String) {
    // Handle login logic here
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    HackerLordAppTheme {
        LoginScreen()
    }
}
