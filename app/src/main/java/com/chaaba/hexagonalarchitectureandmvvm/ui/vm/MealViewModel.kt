package com.chaaba.hexagonalarchitectureandmvvm.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaaba.library.ResourceUiState
import com.chaaba.library.dto.Meal
import com.chaaba.library.repo.MealRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.chaaba.library.Result


class MealViewModel(private val mealRepo: MealRepo) : ViewModel() {

    private val _uiState: MutableStateFlow<ResourceUiState<List<Meal>>> =
        MutableStateFlow(ResourceUiState.Empty)
    val uiState = _uiState.asStateFlow()

    init {
        getMeals()
    }

    private fun getMeals() = viewModelScope.launch {
        _uiState.emit(ResourceUiState.Loading)
        mealRepo
            .getMeals()
            .catch {
                _uiState.emit(ResourceUiState.Error(it.message, it))
            }.collect { result ->
                when (result) {
                    is Result.Success -> {
                        result.value.let {
                            _uiState.emit(ResourceUiState.Success(it))
                            mealRepo.insertMeal(it)
                        }
                    }

                    is Result.Failure -> {
                        _uiState.emit(ResourceUiState.Error(result.message, result.throwable))
                    }
                }
            }
    }
}