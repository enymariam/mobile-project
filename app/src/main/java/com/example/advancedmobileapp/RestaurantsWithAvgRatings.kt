package com.example.advancedmobileapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.advancedmobileapp.models.RestaurantWithAvgRatingDto
import com.example.advancedmobileapp.models.RestaurantWithAvgRatingsState
import com.example.advancedmobileapp.vm.RestaurantsWithAvgRatingsViewModel

@Composable
fun RestaurantsWithAvgRatingsRoot(modifier: Modifier = Modifier,
                                  onNavigate: (Int) -> Unit,
                                  viewmodel:RestaurantsWithAvgRatingsViewModel) {

    val ratingsState by viewmodel.ratingsState.collectAsStateWithLifecycle()

    RestaurantsWithAvgRatingsScreen(state = ratingsState, onNavigate = onNavigate)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantsWithAvgRatingsScreen(modifier: Modifier = Modifier,
                                    state: RestaurantWithAvgRatingsState,
                                    onNavigate: (Int) -> Unit) {
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
                        RestaurantWithAvgRatingsItem(
                            item = rating,
                            onNavigate = onNavigate)

                } }
            }
        }
    }
}

@Composable
fun RestaurantWithAvgRatingsItem(modifier: Modifier = Modifier,
                                 item: RestaurantWithAvgRatingDto,
                                 onNavigate: (Int) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable{
            onNavigate(item.id)

    }) {
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
            Column(modifier = Modifier
                .weight(1f)
                .padding(12.dp, 2.dp)) {
                Text(item.name,
                    style=MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                RatingRow(rating = item.rating ?: 0f, reviewCount = item.reviewCount)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(item.cuisine, fontWeight = FontWeight.Bold)
                    Text(item.priceRange)
                }
                Text(item.address, color = Color.Gray)
                Text(item.openStatus,
                    fontWeight = FontWeight.Bold,

                    color = Color(0xA85c8a21))
            }
        }
    }
}

@Composable
fun RatingRow(modifier: Modifier = Modifier, rating: Float, reviewCount: Int) {
    val fullStars = rating.toInt()
    val hasHalfStar = rating - fullStars >= 0.5
    val emptyStars = 5 - fullStars - if(hasHalfStar) 1 else 0

    Row(){
        repeat(fullStars) {
            Icon(Icons.Filled.Star, contentDescription = "Full star", tint = Color(0xA8A83262))
        }
        if(hasHalfStar){
            Icon(painterResource(id = R.drawable.star_half), contentDescription = "Half star")
        }
        repeat(emptyStars) {
            Icon(Icons.Filled.Star, contentDescription = "No stars", tint = Color(0xA8d6c3cb))
        }

        Spacer(modifier = Modifier.width(8.dp))
        Text(rating.toString())
        Spacer(modifier = Modifier.width(8.dp))
        Text("($reviewCount)")

    }
}