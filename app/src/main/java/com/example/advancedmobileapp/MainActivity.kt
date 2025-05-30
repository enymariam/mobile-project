package com.example.advancedmobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.compose.rememberNavController
import com.example.advancedmobileapp.ui.theme.AdvancedMobileAppTheme
import com.example.advancedmobileapp.vm.RestaurantsWithAvgRatingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdvancedMobileAppTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                data class DrawerItem(val icon: ImageVector, val label: String) // Use of AI [2]
                val items = listOf(
                    DrawerItem(Icons.Default.Home, "Home"),
                    DrawerItem(Icons.Default.Star, "Rated Restaurants"),
                    DrawerItem(Icons.Default.AccountCircle, "Login")
                    /** TODO
                     * Navigation items do nothing atm.
                     */
                )

                val selectedItem = remember { mutableStateOf(items[0]) }
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet(drawerState) {
                            Column(Modifier.verticalScroll(rememberScrollState())) {
                                Spacer(Modifier.height(12.dp))
                                items.forEach { item ->
                                    NavigationDrawerItem(
                                        icon = { Icon(item.icon, contentDescription = item.label) },
                                        label = { Text(item.label) },
                                        selected = item == selectedItem.value,
                                        onClick = {
                                            scope.launch { drawerState.close() }
                                            selectedItem.value = item
                                        },
                                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                    )
                                }
                            }
                        }
                    }
                ){
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
                                    navGraph.SharedViewModel<RestaurantsWithAvgRatingsViewModel>(navController)
                                RestaurantRoot(viewModel = viewmodel, onNavigateBack = {
                                    navController.navigateUp()
                                })
                            }
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