package com.abra.calculator.model

sealed class CalculatorAction{
    data class Number(val number:Int):CalculatorAction()
    object Reset: CalculatorAction()
    object Delete: CalculatorAction()
    object Decimal: CalculatorAction()
    object Calculate: CalculatorAction()
    object ClipBoard: CalculatorAction()
    data class Operation(val operation: CalculatorOperation): CalculatorAction()
}
