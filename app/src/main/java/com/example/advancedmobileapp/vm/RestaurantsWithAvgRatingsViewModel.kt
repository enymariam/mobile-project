package com.example.advancedmobileapp.vm

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advancedmobileapp.DataApi
import com.example.advancedmobileapp.RestaurantsWithAvgRatingsRoot
import com.example.advancedmobileapp.models.RestaurantWithAvgRatingsState
import com.example.advancedmobileapp.ui.theme.AdvancedMobileAppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantsWithAvgRatingsViewModel @Inject constructor(private val api: DataApi) : ViewModel() {
    private val _ratingsState = MutableStateFlow(RestaurantWithAvgRatingsState())
    val ratingsState = _ratingsState.asStateFlow()

    init {
        getRestaurantsWithRatings()
    }

    private fun getRestaurantsWithRatings() {
        viewModelScope.launch{
            try {
                _ratingsState.update { currentState ->
                    currentState.copy(loading = true, error = null)
                }
                // Get data
                val ratings = api.getRestaurantsWithAvgRatings()
                _ratingsState.update { currentState ->
                    currentState.copy(restaurantsRatings = ratings)
                }
            } catch(e: Exception) {
                _ratingsState.update { currentState ->
                    currentState.copy(error = e.toString())
                }
            } finally {
                _ratingsState.update { currentState ->
                    currentState.copy(loading = false)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RestaurantsWithAvgRatingsPreview() {
    AdvancedMobileAppTheme {
        RestaurantsWithAvgRatingsRoot(onNavigate = {})
    }
}