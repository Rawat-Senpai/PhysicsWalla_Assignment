package com.example.physicswalla_assignment.ui.widgets

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ErrorPopup(message: String) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Error") },
        text = { Text(text = message) },
        confirmButton = {
            TextButton(onClick = { /* Handle dismissal */ }) {
                Text(text = "OK")
            }
        }
    )
}