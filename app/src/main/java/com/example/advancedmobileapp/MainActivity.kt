package com.example.advancedmobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.compose.rememberNavController
import com.example.advancedmobileapp.ui.theme.AdvancedMobileAppTheme
import com.example.advancedmobileapp.vm.RestaurantViewModel
import com.example.advancedmobileapp.vm.RestaurantsWithAvgRatingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdvancedMobileAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, "reviewedRestaurants") {
                    navigation("RestaurantsWithAvgRatings", route = "reviewedRestaurants") {

                        composable("RestaurantsWithAvgRatings") { navGraph ->
                            val viewmodel =
                                navGraph.SharedViewModel<RestaurantsWithAvgRatingsViewModel>(
                                    navController
                                )
                            RestaurantsWithAvgRatingsRoot(
                                viewmodel = viewmodel,
                                onNavigate = {
                                    navController.navigate("Restaurant")
                                })
                        }
                        composable("Restaurant") { navGraph ->
                            val viewmodel =
                                navGraph.SharedViewModel<RestaurantViewModel>(navController)
                            RestaurantRoot(viewModel = viewmodel, onNavigateBack = {
                                navController.navigateUp()
                            })
                        }
                    }
                }
            }
        }
    }

    @Composable
    inline fun <reified T : ViewModel> NavBackStackEntry.SharedViewModel(navController: NavController): T {
        val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
        val parentEntry = remember(this) {
            navController.getBackStackEntry(navGraphRoute)
        }
        return hiltViewModel(parentEntry)
    }
}