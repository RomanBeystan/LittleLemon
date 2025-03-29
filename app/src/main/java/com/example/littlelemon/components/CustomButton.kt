package com.example.littlelemon.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.littlelemon.ui.theme.*

@Composable
fun CustomButton(
    text: String,               // Text to be displayed on the button
    onClick: () -> Unit,        // Action to be performed on click
    buttonColor: Color = PrimaryColorTwo,  // Default color for button
    borderColor: Color = SecondaryColorOne,  // Default color for border
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .padding(0.dp)  // Ensure no external padding
            .border( // Apply the border
                width = 1.dp,
                color = borderColor, // Border color
                shape = MaterialTheme.shapes.medium // Border shape
            ),
        shape = MaterialTheme.shapes.medium, // Button shape
        colors = ButtonDefaults.buttonColors(buttonColor, // Set the button color
        ),
        contentPadding = PaddingValues(0.dp) // No internal padding
    ) {
        Text(
            text = text, // Button text
            color = Gray // Text color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    CustomButton(
        text = "Log out",
        onClick = { /* Handle logout */ }
    ) // Sample button usage
}