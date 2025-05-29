package com.example.advancedmobileapp

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.advancedmobileapp.models.RestaurantRatingsDto
import com.example.advancedmobileapp.models.RestaurantRatingsState
import com.example.advancedmobileapp.models.RestaurantWithAvgRatingDto
import com.example.advancedmobileapp.ui.theme.AdvancedMobileAppTheme
import com.example.advancedmobileapp.vm.RestaurantRatingsViewModel


@Composable
fun RestaurantRatingsRoot(modifier: Modifier = Modifier) {
    val vm = hiltViewModel<RestaurantRatingsViewModel>()
    val restaurantState by vm.restaurantState.collectAsStateWithLifecycle()

    RestaurantRatingsScreen(state = restaurantState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantRatingsScreen(modifier: Modifier = Modifier, state: RestaurantRatingsState) {
    Scaffold(topBar = {
        TopAppBar(title = {
            // Add hamburger navi icon on left
            Text("Restaurant Ratings")
            // Add refresh icon on right
        }, navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Menu, contentDescription = "Open menu")
            }
        })
    }) {  paddingValues -> when {
        state.loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator() }
        }
        else -> {
            state.error?.let { err ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues), contentAlignment = Alignment.Center
                ) {
                    Text(err)
                }
            }
        }
    }
    }
}

@Composable
fun RestaurantRatingsItem(modifier: Modifier = Modifier, restaurant: RestaurantRatingsDto) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row {
            Column() {
restaurant.id
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantRatingsScreenPreview() {
    AdvancedMobileAppTheme {
        val state =
            RestaurantRatingsState(
        restaurantRatings = listOf(
            RestaurantRatingsDto(
                id = 1,
                userId = 1,
                stars = 4,
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                dateRated = "2025-03-26 05:58:05",
            ),
            RestaurantRatingsDto(
                id = 2,
                userId = 2,
                stars = 3,
                description = "Ihan jees",
                dateRated = "2024-07-13 07:43:11",
            ))
        )
        RestaurantRatingsScreen(state = state)
    }

}
