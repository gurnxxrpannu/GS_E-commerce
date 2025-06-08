package com.example.gs_e_commerce

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gs_e_commerce.screens.AuthScreen.AuthScreen
import com.example.gs_e_commerce.screens.AuthScreen.SignUpScreen

@Composable
fun AppNavigation(modifier: Modifier){
    val navController=rememberNavController()

    NavHost(navController=navController,startDestination = "auth") {

        composable("auth"){
           AuthScreen(modifier,navController)
        }

        composable("signup"){
            SignUpScreen(modifier,navController)
        }


    }

}