package com.example.gs_e_commerce.screens.AuthScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*

@Composable
fun AuthScreen(modifier: Modifier = Modifier, navController: NavHostController,authViewModel: AuthViewModel= viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    
    // Get the current context for showing toast
    val context = LocalContext.current
    
    // Create a scroll state
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(26.dp)
            .verticalScroll(scrollState), // Add vertical scrolling
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Lottie Animation
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(com.example.gs_e_commerce.R.raw.animation_authscreen))
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
        
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .size(400.dp)
           //     .padding(bottom = 16.dp)
        )
        
        // Login Form
        Text(
            text = "Welcome to GS STORE",
            style = MaterialTheme.typography.headlineLarge,
          //  modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        
        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        
        // Forgot Password Text (Right-aligned)
        Box(modifier = Modifier.fillMaxWidth()) {
            TextButton(
                onClick = { /* TODO: Implement forgot password logic */ },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Text("Forgot Password?")
            }
        }
        
        // Login Button
        Button(
            onClick = {
                isLoading=true
                authViewModel.login(email, password){
                    success,errorMessage->
                    if(success){
                        isLoading=false
                        Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                        navController.navigate("home"){popUpTo("auth"){inclusive=true} }
                    }else{
                        isLoading=false
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            },
            enabled = !isLoading, // Disable the button if isLoading is true
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text=if(isLoading)"Loging Inn!!!" else "Login") // Fixed button text from "Signup" to "Login"
        }
        
        // Sign Up Text
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            //    .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Don't have an account?")
            TextButton(onClick = { navController.navigate("signup") }) {
                Text("Sign Up")
            }
        }
        
        // Add some space at the bottom for better scrolling experience
        Spacer(modifier = Modifier.height(24.dp))
    }
}