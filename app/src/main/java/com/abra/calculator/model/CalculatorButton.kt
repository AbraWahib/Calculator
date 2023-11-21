package com.abra.calculator.model

import androidx.compose.ui.graphics.vector.ImageVector

data class CalculatorButton(
    val text: String? = null,
    val icon: ImageVector? = null,
    val color: ButtonColor,
    val type: ButtonType
    )
enum class ButtonColor{
    NORMAL,
    ACTION,
    OPERATION
}
enum class ButtonType{
    NUMBER,
    DECIMAL,
    CALCULATE,
    RESET,
    DELETE,
    CLIPBOARD,
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    MODULES,
}