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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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

    // 2. Configurar o NavHost (o container de telas)
    NavHost(
        navController = navController,
        startDestination = NavRoutes.LOGIN, // 3. Definir a rota inicial
        modifier = Modifier.fillMaxSize()
    ) {

       /* // 4. Definir o destino LOGIN
        composable(NavRoutes.LOGIN) {
            // CORRIGIDO: Chamando o Composable WrapperLogin e passando a ação
            WrapperLogin( // <--- CORRIGIDO: Era LoginActivity, agora é WrapperLogin
                onNavigateToHome = {
                    // 5. Chamar a navegação para a rota HOME
                    navController.navigate(NavRoutes.HOME)
                }
            )
        }

        // 4. ADICIONAR: Definir o destino HOME
        composable(NavRoutes.HOME) {
            // O Composable WrapperHome é a tela de destino.
            WrapperHome()
        }*/
    }
}