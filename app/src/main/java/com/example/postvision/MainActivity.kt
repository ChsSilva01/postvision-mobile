// MainActivity.kt
package com.example.postvision

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
                    AppNavigation() // Chama o Composable de Navegação principal
                }
            }
        }
    }
}

// O Composable que contém toda a guia de navegacao
@Composable
fun AppNavigation() {
    // 1. Criar o NavController (o cérebro da navegação)
    val navController = rememberNavController()

    // 1. Obter o ViewModel, que a CameraScreen precisa
    val mainViewModel: MainViewModel = viewModel()

    // 2. Configurar o NavHost (o container de telas)
    NavHost(
        navController = navController,
        startDestination = NavRoutes.LOGIN, // 3. Definir a rota inicial
        modifier = Modifier.fillMaxSize()
    ) {

        // 1. Rota de LOGIN -> HOME
        composable(NavRoutes.LOGIN) {
            WrapperLogin(
                onNavigateToHome = {
                    navController.navigate(NavRoutes.HOME) {
                        // Limpa a pilha para não voltar para o Login
                        popUpTo(NavRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        // 2. Rota de HOME -> CHOOSE_EXERCISE
        composable(NavRoutes.HOME) {
            WrapperHome(
                onNavigateToChooseExercise = {
                    // Navega para a tela de escolha de exercício
                    navController.navigate(NavRoutes.CHOOSE_EXERCISE)
                }
            )
        }

        // 3. Rota de CHOOSE_EXERCISE -> STEP_BY_STEP
        composable(NavRoutes.CHOOSE_EXERCISE) {
            WrapperChooseExercise(
                onNavigateToStepByStep = {
                    // Navega para a tela de passo a passo
                    navController.navigate(NavRoutes.STEP_BY_STEP)
                }
            )
        }

        // 4. Rota de STEP_BY_STEP
        composable(NavRoutes.STEP_BY_STEP) {
            WrapperStepByStep(
                onNavigateToCameraX = {
                    navController.navigate(NavRoutes.CAMERA_SCREEN)
                }
            )
        }

        // 5. Rota da CameraX
        composable(NavRoutes.CAMERA_SCREEN) {
            // Chama a função Composable da tela da Câmera, passando o ViewModel
            CameraScreen().CameraScreenContent(viewModel = mainViewModel)
            // Nota: Se a CameraScreen precisar de um callback de volta, ele deve ser adicionado aqui.
        }
    }
}