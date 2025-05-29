package com.example.advancedmobileapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advancedmobileapp.DataApi
import com.example.advancedmobileapp.models.RestaurantState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(private val api: DataApi) : ViewModel() {
    private val _restaurantState = MutableStateFlow(RestaurantState())
    val restaurantState = _restaurantState.asStateFlow()

    init {
        getRestaurant()
    }

    fun getRestaurant() {
        viewModelScope.launch{
            try {
                _restaurantState.update { currentState ->
                    currentState.copy(loading = true, error = null)
                }
                // Get data
                val restaurant = api.getRestaurant()
                _restaurantState.update { currentState ->
                    currentState.copy(theRestaurant = restaurant)
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
