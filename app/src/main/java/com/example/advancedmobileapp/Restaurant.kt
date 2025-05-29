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
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.advancedmobileapp.models.RestaurantDto
import com.example.advancedmobileapp.models.RestaurantState
import com.example.advancedmobileapp.ui.theme.AdvancedMobileAppTheme
import com.example.advancedmobileapp.vm.RestaurantViewModel


@Composable
fun RestaurantRoot(modifier: Modifier = Modifier,onNavigate: () -> Unit) {
    val vm = hiltViewModel<RestaurantViewModel>()
    val restaurantState by vm.restaurantState.collectAsStateWithLifecycle()

    RestaurantScreen(state = restaurantState, onNavigate = onNavigate)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantScreen(modifier: Modifier = Modifier, state: RestaurantState, onNavigate: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Restaurant Ratings") // Restaurant title here
        }, navigationIcon = {
            IconButton(onClick = onNavigate) {
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
                horizontalAlignment = Alignment.CenterHorizontally){
                items(state.theRestaurant,
                    key = {restaurant -> restaurant.id }
                ) { restaurant ->
                    RestaurantItem(item = restaurant)

                }
            }
        }
    }
    }
}

@Composable
fun RestaurantItem(modifier: Modifier = Modifier, item: RestaurantDto) {
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

@Preview(showBackground = true)
@Composable
fun RestaurantScreenPreview() {
    AdvancedMobileAppTheme {
        val state =
            RestaurantState(
        theRestaurant = listOf(
            RestaurantDto(
                id = 1,
                userId = 1,
                value = 4,
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                dateRated = "2025-03-26 05:58:05",
            ),
            RestaurantDto(
                id = 2,
                userId = 2,
                value = 3.5,
                description = "Ihan jees",
                dateRated = "2024-07-13 07:43:11",
            ))
        )
        RestaurantScreen(state = state, onNavigate = {})
    }
}
