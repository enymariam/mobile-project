package com.example.advancedmobileapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advancedmobileapp.DataApi
import com.example.advancedmobileapp.models.RestaurantRatingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantRatingsViewModel @Inject constructor(private val api: DataApi) : ViewModel() {
    private val _restaurantState = MutableStateFlow(RestaurantRatingsState())
    val restaurantState = _restaurantState.asStateFlow()

    init {
        getRestaurantRatings()
    }

    private fun getRestaurantRatings() {
        viewModelScope.launch{
            try {
                _restaurantState.update { currentState ->
                    currentState.copy(loading = true, error = null)
                }
                // Get data
                val restaurant = api.getRestaurantRatings()
                _restaurantState.update { currentState ->
                    currentState.copy(restaurantRatings = restaurant)
                }
            } catch(e: Exception) {
                _restaurantState.update { currentState ->
                    currentState.copy(error = e.toString())
                }
            } finally {
                _restaurantState.update { currentState ->
                    currentState.copy(loading = false)
                }
            }
        }
    }
}
