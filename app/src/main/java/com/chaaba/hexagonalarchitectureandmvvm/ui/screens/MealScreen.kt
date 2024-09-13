package com.chaaba.hexagonalarchitectureandmvvm.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.chaaba.hexagonalarchitectureandmvvm.ui.componants.MealItem
import com.chaaba.hexagonalarchitectureandmvvm.ui.vm.MealViewModel
import com.chaaba.library.ResourceUiState
import com.chaaba.library.dto.Meal
import org.koin.compose.koinInject


@Composable
fun MealScreen(modifier: Modifier) {
    val mealViewModel: MealViewModel = koinInject()

    val uiState by mealViewModel.uiState.collectAsState()

    var meals by remember {
        mutableStateOf<List<Meal>>(emptyList())
    }

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            ResourceUiState.Empty -> {
                println("Empty")
            }
            is ResourceUiState.Error -> {
                println(state.message)
            }
            ResourceUiState.Loading -> {
                println("Loading")
            }
            is ResourceUiState.Success -> {
                meals = state.data
            }
        }
    }
    LazyColumn(modifier = modifier) {
        items(meals.size) {
            MealItem(category = meals[it])
        }
    }
}