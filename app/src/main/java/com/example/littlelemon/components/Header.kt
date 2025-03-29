package com.ilyes.littlelemon.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.R
import com.ilyes.littlelemon.Profile
import com.ilyes.littlelemon.UserSession

@Composable
fun Header(navController: NavController) {
    val context = LocalContext.current

    val isLoggedIn = UserSession.isLoggedIn(context)


    Column() {

     Row(
         modifier = Modifier
             .fillMaxWidth()
             .height(75.dp),
         verticalAlignment = Alignment.CenterVertically
     ) {
         Image(
             painter = painterResource(id = R.drawable.logo),
             contentDescription = "Logo",
             modifier = Modifier
                 .height(45.dp)
                 .weight(0.8f)
         )

        if (isLoggedIn) {
             Image(
                 painter = painterResource(id = R.drawable.profileimage),
                 contentDescription = "Logo",
                 modifier = Modifier
                     .height(50.dp)
                     .width(50.dp)
                     .weight(0.2f)
                     .clip(shape = RoundedCornerShape(50.dp))
                     .clickable { navController.navigate(Profile.route) },
                 contentScale = ContentScale.Fit
             )
         }
     }
 }
}


@Preview(showBackground = true)
@Composable
fun HeaderPreview()
{
    val navController = rememberNavController()

    Header(navController)
}
