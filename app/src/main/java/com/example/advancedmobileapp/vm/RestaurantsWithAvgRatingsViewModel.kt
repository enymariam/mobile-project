package com.example.advancedmobileapp.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advancedmobileapp.DataApi
import com.example.advancedmobileapp.models.RestaurantDto

import com.example.advancedmobileapp.models.RestaurantState
import com.example.advancedmobileapp.models.RestaurantWithAvgRatingsState
import com.example.advancedmobileapp.models.RestaurantsDto

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantsWithAvgRatingsViewModel @Inject constructor(
    private val api: DataApi,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel()
{
    private val _ratingsState = MutableStateFlow(RestaurantWithAvgRatingsState())
    val ratingsState = _ratingsState.asStateFlow()

    private val _restaurantState = MutableStateFlow(RestaurantState())
    val restaurantState = _restaurantState.asStateFlow()

    init {
        getRestaurantsWithRatings()
    }

    fun getRestaurant() {
        viewModelScope.launch{
            try {
                _restaurantState.update { currentState ->
                    currentState.copy(loading = true, error = null)
                }
                // Get data
                savedStateHandle.get<Int>("id")?.let{ pId ->
                    val restaurant = api.getRestaurant(pId)
                    _restaurantState.update { currentState ->
                        currentState.copy(theRestaurant = RestaurantsDto(name = "", review = restaurant))
                    }
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

    fun setRestaurantId(id: Int){
        savedStateHandle["id"] = id
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