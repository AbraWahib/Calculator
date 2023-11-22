package com.abra.calculator.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.abra.calculator.model.CalculatorAction
import com.abra.calculator.model.CalculatorOperation
import com.abra.calculator.model.CalculatorState

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set

    private fun performCalculation() {
        val number1 = state.firstNumber.toDoubleOrNull()
        val number2 = state.secondNumber.toDoubleOrNull()
        if (number1 != null && number2 != null) {
            val result = when (state.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                is CalculatorOperation.Modules -> number1 % number2
                null -> return
            }
            state = state.copy(
                firstNumber = result.toString().take(15),
                secondNumber = "",
                operation = null
            )
        }


    }


    private fun performDeletion() {
        when {
            state.secondNumber.isNotBlank() -> state =
                state.copy(secondNumber = state.secondNumber.dropLast(1))

            state.operation != null -> state = state.copy(operation = null)

            state.firstNumber.isNotBlank() -> state =
                state.copy(firstNumber = state.firstNumber.dropLast(1))
        }

    }

    private fun enterDecimal() {
        if (state.operation == null && !state.firstNumber.contains(".") && state.firstNumber.isNotBlank()) {
            state = state.copy(firstNumber = state.firstNumber + ".")
            return
        }
        if (!state.secondNumber.contains(".") && state.secondNumber.isNotBlank()) {
            state = state.copy(secondNumber = state.secondNumber + ".")
        }
    }

    private fun copyToClipboard() {
        return
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            if (state.firstNumber.length >= 8) return
            state = state.copy(firstNumber = state.firstNumber + number)
            return
        }
        if (state.secondNumber.length > 8) return
        state = state.copy(secondNumber = state.secondNumber + number)
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.firstNumber.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Delete -> performDeletion()
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.ClipBoard -> copyToClipboard()
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Reset -> state = CalculatorState()
        }
    }
}




