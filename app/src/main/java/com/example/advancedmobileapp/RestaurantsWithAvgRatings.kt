package com.example.advancedmobileapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.advancedmobileapp.models.RestaurantWithAvgRatingDto
import com.example.advancedmobileapp.models.RestaurantWithAvgRatingsState
import com.example.advancedmobileapp.vm.RestaurantsWithAvgRatingsViewModel

@Composable
fun RestaurantsWithAvgRatingsRoot(modifier: Modifier = Modifier) {
    val vm = hiltViewModel<RestaurantsWithAvgRatingsViewModel>()
    val ratingsState by vm.ratingsState.collectAsStateWithLifecycle()

    RestaurantsWithAvgRatingsScreen(state = ratingsState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantsWithAvgRatingsScreen(modifier: Modifier = Modifier, state: RestaurantWithAvgRatingsState) {
Scaffold(topBar = {
    TopAppBar(title = {
        // Add hamburger navi icon on left
        Text("Restaurants")
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
                } ?: LazyColumn (modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    items(state.restaurantsRatings, key = {rating ->
                    rating.id
                }) { rating ->
                        RestaurantWithAvgRatingsItem(rating = rating)

                } }
            }
        }
    }
}

@Composable
fun RestaurantWithAvgRatingsItem(modifier: Modifier = Modifier, rating: RestaurantWithAvgRatingDto) {
    Card(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
        Row (modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)){
            AsyncImage(model = R.drawable.review,
                contentDescription = "Placeholder Image",
                modifier = Modifier
                    .size(150.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column() {
                Text(rating.name)
                // Stars - Rating - (reviewCount)
                Text(rating.cuisine)
                Text(rating.priceRange)
                Text(rating.address)
                Text(rating.openStatus)
            }
        }
    }
}