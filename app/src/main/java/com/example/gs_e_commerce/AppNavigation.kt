package com.example.gs_e_commerce

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gs_e_commerce.screens.AuthScreen.AuthScreen
import com.example.gs_e_commerce.screens.AuthScreen.SignUpScreen
import com.example.gs_e_commerce.screens.Homescreen.HomeScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun AppNavigation(modifier: Modifier){
    val navController=rememberNavController()

    val isLoggedIn= Firebase.auth.currentUser!=null

    val firstPage=if(isLoggedIn)"home" else "auth"

    NavHost(navController=navController,startDestination = firstPage) {

        composable("auth"){
           AuthScreen(modifier,navController)
        }

        composable("signup"){
            SignUpScreen(modifier,navController)
        }
        composable("home"){
            HomeScreen(modifier,navController)
        }


    }

}