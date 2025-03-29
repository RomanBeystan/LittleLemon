package com.example.littlelemon.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.*
import com.ilyes.littlelemon.MenuItemEntity

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(item: MenuItemEntity) {
    Column(modifier = Modifier.padding(5.dp)) {

        Text(text = item.title,style = TypographyLittleLemon.subtitle1)
        Row{
            Column(modifier = Modifier.weight(0.8F)) {
                Text(text = item.description, modifier = Modifier.padding(top = 8.dp),style = TypographyLittleLemon.caption)
                Text(text = "$${item.price}")
            }
            GlideImage(
                model = item.image,
                contentDescription = item.title,
                modifier = Modifier
                    .weight(0.2F).fillMaxHeight()
            )
        }


    }
}

@Composable
fun MenuItems(menuItems: List<MenuItemEntity>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 15.dp, top = 20.dp, end = 15.dp)
    ) {
        items(
            items = menuItems,
            itemContent = { menuItem ->
                MenuItem(menuItem)
                Divider(color = SecondaryColorThree)
                Spacer(modifier = Modifier.height(8.dp))
            }
        )
    }
}



@Composable
fun MenuCategories(categories: Set<String>, categoryLambda: (sel: String) -> Unit) {
    val selectedCategory = remember { mutableStateOf("") }

    Card(elevation = 1.dp, modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
            Text(text = "ORDER FOR DELIVERY", fontWeight = FontWeight.ExtraBold)

            LazyRow(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items = categories.toList()) { category ->
                    CategoryButton(
                        category = category,
                        isSelected = selectedCategory.value == category,
                        onCategorySelected = {
                            if (selectedCategory.value == category) {
                                selectedCategory.value = ""
                            } else {
                                selectedCategory.value = category
                            }
                            categoryLambda(category)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryButton(category: String, isSelected: Boolean, onCategorySelected: (String) -> Unit) {
    Button(
        onClick = {
            onCategorySelected(category)
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = if (isSelected) SecondaryColorThree else PrimaryColorOne ,
            backgroundColor = if (isSelected) PrimaryColorOne else SecondaryColorThree
        ),
        shape = Shapes.large
    ) {
        Text(text = category, fontWeight = FontWeight.Bold)
    }
}