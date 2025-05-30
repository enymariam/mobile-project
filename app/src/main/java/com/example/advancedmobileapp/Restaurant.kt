package com.example.advancedmobileapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.advancedmobileapp.models.RestaurantDto
import com.example.advancedmobileapp.models.RestaurantState
import com.example.advancedmobileapp.models.RestaurantsDto
import com.example.advancedmobileapp.vm.RestaurantsWithAvgRatingsViewModel

@Composable
fun RestaurantRoot(
    modifier: Modifier = Modifier,
    viewModel: RestaurantsWithAvgRatingsViewModel,
    onNavigateBack: () -> Unit
    ) {
    val restaurantState by viewModel.restaurantState.collectAsStateWithLifecycle()
    RestaurantScreen(
        state = restaurantState,
        onNavigateBack = {
            viewModel.getRestaurant()
            onNavigateBack()
        })

    LaunchedEffect(Unit){
        viewModel.getRestaurant()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantScreen(
    modifier: Modifier = Modifier,
    state: RestaurantState,
    onNavigateBack: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Restaurant") // Restaurant title here
        }, navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
            }
        }
        )
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
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                items(state.theRestaurant?.review?: emptyList(),
                    key = {r -> r.id }
                ) { restaurant ->
                    RestaurantItem(item = restaurant)

                }
            }
        }
    }
    }
}

@Composable
fun RestaurantItem(
    modifier: Modifier = Modifier,
    item: RestaurantDto
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)){

            Column(modifier = Modifier.padding(12.dp, 2.dp)) {

                ReviewRow(rating = item.value?.toFloat() ?: 0f)

                Text(item.description ?: "")
                Spacer(modifier = Modifier.height(10.dp))
                Text(item.dateRated,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun ReviewRow(modifier: Modifier = Modifier, rating: Float) {
    val fullStars = rating.toInt()
    val hasHalfStar = rating - fullStars >= 0.5
    val emptyStars = 5 - fullStars - if(hasHalfStar) 1 else 0

    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)){
        Row(modifier = Modifier.weight(2f)) {
            repeat(fullStars) {
                Icon(Icons.Filled.Star, contentDescription = "Full star", tint = Color(0xA8A83262))
            }
            if (hasHalfStar) {
                Icon(painterResource(id = R.drawable.star_half), contentDescription = "Half star",tint = Color(0xA8A83262))
            }
            repeat(emptyStars) {
                Icon(Icons.Filled.Star, contentDescription = "No stars", tint = Color(0xA8d6c3cb))
            }

            Spacer(modifier = Modifier.width(10.dp))
            Text(rating.toString())
        }
        Row() {
            Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = Color(0xA8A83262))
        }

    }
}
