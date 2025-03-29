package com.ilyes.littlelemon.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemon.components.MenuCategories

import com.example.littlelemon.components.MenuItems
import com.example.littlelemon.ui.theme.PrimaryColorOne
import com.example.littlelemon.ui.theme.SecondaryColorThree
import com.example.littlelemon.ui.theme.Shapes
import com.example.littlelemon.ui.theme.TypographyLittleLemon
import com.ilyes.littlelemon.MenuItemEntity

import com.ilyes.littlelemon.components.Header
import java.util.*


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(navController: NavController, menuItems: List<MenuItemEntity>) {

    var searchPhrase by remember { mutableStateOf("") }
    val selectedCategory = remember {
        mutableStateOf("")
    }

    val filteredCategoryItems = menuItems.map {
        it.category.replaceFirstChar { character ->
            character.uppercase()
        }
    }.toSet()


    val items = if (searchPhrase == "") {
        menuItems

    } else {
        menuItems.filter {
            it.title.contains(searchPhrase, ignoreCase = true)

        }


    }


    val filteredMenuItems = if (selectedCategory.value == "") {
        items
    } else {
        items.filter {
            it.category.contains(selectedCategory.value, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Header(navController = navController)

        HeroSection(searchPhrase = searchPhrase, onSearchPhraseChange = { newSearchPhrase ->
            searchPhrase = newSearchPhrase
        })

        MenuCategories(filteredCategoryItems) { category ->
            selectedCategory.value = if (selectedCategory.value == category) {
                ""
            } else {
                category
            }
        }

        MenuItems(menuItems = filteredMenuItems)
    }
}

@Composable
fun HeroSection(searchPhrase: String, onSearchPhraseChange: (String) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(PrimaryColorOne)
            .padding(start = 10.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {


            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(horizontal = 10.dp, vertical = 0.dp)
            ) {

                Text(text = "Little Lemon", style = TypographyLittleLemon.h3)
                Text(
                    text = "Chicago", style = TypographyLittleLemon.h4, color = SecondaryColorThree
                )
                Text(
                    text = "We are a family-owned Mediterranean restaurant," + " focused on traditional " + "recipes served with a modern twist",
                    style = TypographyLittleLemon.h6, color = SecondaryColorThree,
                )


            }

            Image(
                painter = painterResource(id = com.example.littlelemon.R.drawable.hero),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp)
                    .clip(shape = Shapes.large)
                    .height(120.dp)
                    .width(120.dp)
                    .weight(0.4f),
                contentScale = ContentScale.Crop,
                contentDescription = "Restaurant Hero Image"
            )


        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .background(color = SecondaryColorThree)
                .clip(shape = Shapes.large),
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "search phrase") },
            value = searchPhrase,
            singleLine = true,
            placeholder = { Text("Enter search phrase") },
            onValueChange = { newSearchPhrase ->
                onSearchPhraseChange(newSearchPhrase)
            },
        )

    }


}