package com.example.advancedmobileapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.advancedmobileapp.models.RatingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RatingsScreenViewModel: ViewModel() {
    private val _ratingsState = MutableStateFlow(RatingsState())
    val ratingsState = _ratingsState.asStateFlow()

    init {
        getRatings()
    }

    private fun getRatings() {
        viewModelScope.launch{
            try {
                _ratingsState.update { currentState ->
                    currentState.copy(loading = true, error = null)
                }
                // Get data
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