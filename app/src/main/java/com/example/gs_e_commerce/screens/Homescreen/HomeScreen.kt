package com.example.gs_e_commerce.screens.Homescreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.gs_e_commerce.pages.CartPage
import com.example.gs_e_commerce.pages.FavouritesPage
import com.example.gs_e_commerce.pages.HomePage
import com.example.gs_e_commerce.pages.ProfilePage


@Composable
fun HomeScreen(modifier: Modifier, navController: NavController){

    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Favorites", Icons.Default.Favorite),
        NavItem("Cart", Icons.Default.ShoppingCart),
        NavItem("Profile", Icons.Default.Person)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = index == selectedIndex ,
                        onClick = {
                            selectedIndex=index

                        },
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = navItem.label
                            )
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) {
        ContentScreen(modifier = modifier.padding(it), navController = navController, selectedIndex = selectedIndex)

    }
}

@Composable
fun ContentScreen(modifier: Modifier, navController: NavController, selectedIndex: Int) {
    when (selectedIndex) {
        0 -> HomePage(modifier)
        1 -> FavouritesPage(modifier)
        2 -> CartPage(modifier)
        3 -> ProfilePage(modifier)
    }
}


data class NavItem(
    val label: String,
    val icon:ImageVector
)