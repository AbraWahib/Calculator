package com.abra.calculator.model

data class CalculatorState(
    val firstNumber: String = "",
    val secondNumber : String = "",
    val operation: CalculatorOperation? = null
)
