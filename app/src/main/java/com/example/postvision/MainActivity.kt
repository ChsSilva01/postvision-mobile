// MainActivity.kt
package com.example.postvision

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.postvision.camerax.CameraScreen
import com.example.postvision.camerax.MainViewModel
import com.example.postvision.navigation.NavRoutes
import com.example.postvision.ui.theme.PostVisionTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostVisionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val mainViewModel: MainViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.LOGIN,
        modifier = Modifier.fillMaxSize(),
        /*exitTransition = slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth},
            animationSpec =
        )*/
    ) {

        composable(NavRoutes.LOGIN) {
            WrapperLogin(
                onNavigateToHome = {
                    navController.navigate(NavRoutes.HOME) {
                        popUpTo(NavRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.HOME) {
            WrapperHome(
                onNavigateToChooseExercise = {
                    navController.navigate(NavRoutes.CHOOSE_EXERCISE)
                }
            )
        }

        composable(NavRoutes.CHOOSE_EXERCISE) {
            WrapperChooseExercise(
                onNavigateToStepByStep = {
                    navController.navigate(NavRoutes.STEP_BY_STEP)
                }
            )
        }

        composable(NavRoutes.STEP_BY_STEP) {
            WrapperStepByStep(
                onNavigateToCameraX = {
                    navController.navigate(NavRoutes.CAMERA_SCREEN)
                }
            )
        }

        composable(NavRoutes.CAMERA_SCREEN) {
            CameraScreen().CameraScreenContent(viewModel = mainViewModel)
        }
    }
}