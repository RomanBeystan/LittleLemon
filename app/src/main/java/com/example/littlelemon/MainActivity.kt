package com.example.littlelemon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.ilyes.littlelemon.*
import com.ilyes.littlelemon.components.Header
import com.ilyes.littlelemon.screen.HomeScreen
import com.ilyes.littlelemon.screen.Onboarding
import com.ilyes.littlelemon.screen.ProfileScreen
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            }, contentType = ContentType.Text.Plain) // Force text/plain handling
        }
    }
    private val menuItemsLiveData = MutableLiveData<List<MenuItemNetwork>>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = applicationContext

        val database = provideDatabase(context)
        val menuDao = database.menuDao()

        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuDao().isEmpty()) {

                val menuItems = getMenu()
                val menuItemEntities = menuItems.map {
                    MenuItemEntity(
                        id = it.id,
                        title = it.title,
                        description = it.description,
                        price = it.price,
                        image = it.image,
                        category = it.category
                    )
                }
                menuDao.insertMenuItems(menuItemEntities) // Save to database

                menuItemsLiveData.postValue(menuItems)
            }
        }



        setContent {
            LittleLemonTheme {



                val databaseMenuItems by database.menuDao().getAllMenuItems().observeAsState(emptyList())





                val menuItems = databaseMenuItems

                AppScreen(menuItems)
            }
        }
    }


    private suspend fun getMenu(): List<MenuItemNetwork> {
        val response: MenuNetwork = client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body()
        return response.menu.map {
            MenuItemNetwork(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price,
                image = it.image,
                category = it.category
            )
        }
    }

}


@Composable
private fun AppScreen(menuItems: List<MenuItemEntity>) {
    val navController = rememberNavController()


    Scaffold(

    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            val context = LocalContext.current


            // Check if the user is logged in on app launch
            val isLoggedIn = UserSession.isLoggedIn(context)

            // NavHost - Navigation graph
            NavHost(navController = navController, startDestination = if (isLoggedIn) Home.route else Onboarding.route) {
                composable(Onboarding.route) {
                    Onboarding(navController, context)
                }
                composable(Home.route) {
                    HomeScreen(navController,menuItems)
                }
                composable(Profile.route) {
                    ProfileScreen(navController, context)
                }
            }



        }
    }
}








