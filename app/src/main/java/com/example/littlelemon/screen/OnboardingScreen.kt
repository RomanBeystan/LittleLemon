package com.ilyes.littlelemon.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color // Add this import for Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.littlelemon.components.CustomButton
import com.example.littlelemon.ui.theme.*
import com.ilyes.littlelemon.Home
import com.ilyes.littlelemon.Onboarding
import com.ilyes.littlelemon.UserSession
import com.ilyes.littlelemon.components.Header

@Composable
fun Onboarding(navController: NavController, context: Context) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var messageColor by remember { mutableStateOf(Color.Transparent) } // Initialize with Transparent color



    Column(
        modifier = Modifier

            .fillMaxWidth()
    ) {
        Header(navController=navController)

        Box(
            modifier = Modifier
                .background(PrimaryColorOne)
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Let's get to know you",
                color = SecondaryColorThree,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp
            )
        }

        // Input Fields
        Column(modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()) {

            Text(
                text = "Personal information",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 25.dp, top = 25.dp)
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First name") }
            )
            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last name") }
            )
            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }
            )

            // Show message based on registration status
            Text(
                text = message,
                color = messageColor,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(15.dp)
            )

            CustomButton(text = "Register" , onClick = {
                // Validate input fields
                if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                    message = "Registration unsuccessful. Please enter all data."
                    messageColor = Red
                } else {
                    // Save user data in SharedPreferences
                    UserSession.saveUserData(context, firstName, lastName, email)
                    UserSession.setLoggedIn(context, true)
                    message = "Registration successful!"
                    messageColor = Green


                    // Navigate to Home Screen
                    navController.navigate(Home.route) {
                        popUpTo(Onboarding.route) { inclusive = true }
                    }
                }
            })


        }




    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    val navController = rememberNavController()

    Onboarding(navController, context = LocalContext.current) // Pass context to Onboarding
}
