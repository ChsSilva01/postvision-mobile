package com.example.postvision.camerax

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.*
import com.example.postvision.R // Necessário para R.drawable.ic_menu_camera

class AppNavigator {
    // Mantém apenas a Camera. A Galeria foi removida.
    sealed class Screen(val route: String, val label: String, @DrawableRes val icon: Int) {
        data object Camera : Screen(
            "camera",
            "Camera",
            // Use R.drawable.ic_menu_camera ou crie um VectorDrawable
            // Para exemplo, estou usando um placeholder que requer o import acima.
            R.drawable.ic_baseline_photo_camera_24 // <-- Assumindo que você criou este drawable
        )
    }

    private val items = listOf(
        Screen.Camera,
    )


    /**
     * Configura a navegação da aplicação com um NavController.
     *
     * @param viewModel El ViewModel principal de la aplicación.
     */
    @Composable
    fun Navigator(viewModel: MainViewModel) {
        val navController = rememberNavController()

        Scaffold(
            // Removi a bottomBar, pois agora só há 1 item de navegação
            // Se você quiser manter a bottomBar com apenas 1 item, descomente o bloco abaixo:
            /*
            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = screen.label
                            ) },
                            label = { Text(screen.label) },
                            selected = currentDestination?.route == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
            */
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Camera.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                // Mantém apenas a tela da Câmera
                composable(Screen.Camera.route) { CameraScreen().CameraScreenContent(viewModel = viewModel) }
                // O composable da Galeria foi removido
            }
        }
    }
}