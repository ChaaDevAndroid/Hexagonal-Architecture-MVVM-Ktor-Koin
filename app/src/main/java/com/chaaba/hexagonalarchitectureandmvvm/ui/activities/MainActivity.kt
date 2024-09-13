package com.chaaba.hexagonalarchitectureandmvvm.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.chaaba.hexagonalarchitectureandmvvm.ui.screens.MealScreen
import com.chaaba.hexagonalarchitectureandmvvm.ui.theme.HexagonalArchitectureAndMVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HexagonalArchitectureAndMVVMTheme {
                Scaffold {
                    MealScreen(Modifier.padding(it))
                }
            }
        }
    }
}



