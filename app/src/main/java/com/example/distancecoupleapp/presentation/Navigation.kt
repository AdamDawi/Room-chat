package com.example.distancecoupleapp.presentation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.distancecoupleapp.presentation.camera.CameraScreen
import com.example.distancecoupleapp.presentation.comments.CommentsScreen
import com.example.distancecoupleapp.presentation.login.LoginScreen
import com.example.distancecoupleapp.presentation.main_board.MainBoardScreen
import com.example.distancecoupleapp.presentation.search_user.SearchUserScreen

@Composable
fun Navigation(
    context: Context
) {
    // Initialize NavController using rememberNavController() function, which allows maintaining the navigation state
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route ){

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                viewModel(),
                context,
                // Navigate to the user search screen after successful login
                navigateToMainPhotosScreen = { navController.navigate(Screen.SearchUserScreen.route) }
            )
        }

        composable(route = Screen.SearchUserScreen.route){
            SearchUserScreen(
                viewModel(),
                // Navigate to the previous screen (login screen)
                navigateToLoginScreen = { navController.popBackStack() },
                // Pass NavController to the user search screen
                navigateToMainBoardScreen = navController
            )
        }

        composable(route = Screen.MainBoardScreen.route+ "/{roomId}", arguments =
        listOf(
            navArgument("roomId"){
                type = NavType.StringType
                defaultValue = "Error"
                nullable = false
            }
        )
        ){
            // Retrieve room ID from the navigation arguments
            val roomId = it.arguments?.getString("roomId")?:"Error"
            MainBoardScreen(viewModel(), navController, roomId)
        }

        composable(route = Screen.CommentsScreen.route+ "/{roomId}/{photoId}", arguments =
        listOf(
            navArgument("roomId"){
                type = NavType.StringType
                defaultValue = "Error"
                nullable = false
            },
            navArgument("photoId"){
                type = NavType.StringType
                defaultValue = "Error"
                nullable = false
            }
        )
        ){
            // Retrieve room ID and photo ID from the navigation arguments
            val roomId = it.arguments?.getString("roomId")?:"Error"
            val photoId = it.arguments?.getString("photoId")?:"Error"
            CommentsScreen(viewModel(), roomId, photoId)
        }

        composable(route = Screen.CameraScreen.route+ "/{roomId}", arguments =
        listOf(
            navArgument("roomId"){
                type = NavType.StringType
                defaultValue = "Error"
                nullable = false
            })){
            // Retrieve room ID from the navigation arguments
            val roomId = it.arguments?.getString("roomId")?:"Error"
            CameraScreen(viewModel(), context, navController, roomId)
        }
    }


}