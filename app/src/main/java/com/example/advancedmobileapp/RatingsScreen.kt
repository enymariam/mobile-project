package com.example.advancedmobileapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.advancedmobileapp.models.RatingDto
import com.example.advancedmobileapp.models.RatingsState
import com.example.advancedmobileapp.vm.RatingsScreenViewModel

@Composable
fun RatingsScreenRoot(modifier: Modifier = Modifier) {
    val vm = hiltViewModel<RatingsScreenViewModel>()
    val ratingsState by vm.ratingsState.collectAsStateWithLifecycle()

    RatingsScreen(state = ratingsState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingsScreen(modifier: Modifier = Modifier, state: RatingsState) {
Scaffold(topBar = {
    TopAppBar(title = {
        // Add hamburger navi icon on left
        Text("Restaurants")
        // Add refresh icon on right
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
                } ?: LazyColumn (modifier = Modifier.fillMaxSize().padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    items(state.ratings, key = {rating ->
                    rating.id
                }) { rating ->
                        RatingItem(rating = rating)

                } }
            }
        }
    }
}

@Composable
fun RatingItem(modifier: Modifier = Modifier, rating: RatingDto) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row {
            Column() {
                // Image
                Text(rating.name)
                // Stars - Rating - (reviewCount)
                Text(rating.cuisine)
                Text(rating.price_range)
                Text(rating.address)
                Text(rating.open_status)
            }
        }
    }
}