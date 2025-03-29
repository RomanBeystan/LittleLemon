package com.ilyes.littlelemon.screen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.components.CustomButton
import com.ilyes.littlelemon.Home
import com.ilyes.littlelemon.Onboarding
import com.ilyes.littlelemon.UserSession

@Composable
fun ProfileScreen(navController: NavController, context: Context) {

    val userData = UserSession.getUserData(context)





        Column(
            modifier = Modifier
                .padding(vertical = 55.dp, horizontal = 15.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = "Personal information",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 25.dp, top = 25.dp)
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = userData.firstName,
                readOnly = true,
                onValueChange = {},
                label = { Text("First name") }
            )
            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = userData.lastName,
                readOnly = true,
                onValueChange = {},
                label = { Text("Last name") }
            )
            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = userData.email,
                readOnly = true,
                onValueChange = {},
                label = { Text("Email") }
            )
            Spacer(modifier = Modifier.height(30.dp))


            CustomButton(
                text = "Log out",
                onClick = { // Log the user out and navigate back to Onboarding screen
                    UserSession.setLoggedIn(context, false)
                    navController.navigate(Onboarding.route) {
                        popUpTo(Home.route) { inclusive = true }
                    }
                })
        }


}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()

    ProfileScreen(navController, context = LocalContext.current) // Pass context to Onboarding
}
