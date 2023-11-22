package com.abra.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.abra.calculator.ui.component.Calculator
import com.abra.calculator.ui.theme.CalculatorTheme
import com.abra.calculator.viewModel.CalculatorViewModel
import com.abra.calculator.viewModel.LocalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                val themeViewModel = LocalTheme.current
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Calculator(
                        state = state,
                        onAction = viewModel::onAction,
                        themeViewModel = themeViewModel,
                        )

                }
            }
        }
    }
}
