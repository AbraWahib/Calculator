package com.abra.calculator.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abra.calculator.model.ButtonColor
import com.abra.calculator.model.ButtonType
import com.abra.calculator.model.CalculatorAction
import com.abra.calculator.model.CalculatorOperation
import com.abra.calculator.model.CalculatorButton
import com.abra.calculator.model.CalculatorState
import com.abra.calculator.ui.theme.Cyan
import com.abra.calculator.ui.theme.Orange
import com.abra.calculator.viewModel.ThemeViewModel

@Composable
fun Calculator(state: CalculatorState,
               onAction: (CalculatorAction) -> Unit,
               themeViewModel: ThemeViewModel,
               modifier: Modifier = Modifier) {

    //list of button objects that will be passed to LazyGrid
    val calculatorButtons by remember {
        mutableStateOf(
            listOf(
                CalculatorButton(text = "C", color = ButtonColor.ACTION,type = ButtonType.RESET),
                CalculatorButton(icon = Icons.Outlined.Backspace, color = ButtonColor.ACTION,type = ButtonType.DELETE),
                CalculatorButton(text = "%", color = ButtonColor.ACTION,type = ButtonType.MODULES),
                CalculatorButton(text = "÷", color = ButtonColor.OPERATION,type = ButtonType.DIVIDE),
                CalculatorButton(text = "7", color = ButtonColor.NORMAL,type = ButtonType.NUMBER),
                CalculatorButton(text = "8", color = ButtonColor.NORMAL,type = ButtonType.NUMBER),
                CalculatorButton(text = "9", color = ButtonColor.NORMAL,type = ButtonType.NUMBER),
                CalculatorButton(text = "x", color = ButtonColor.OPERATION,type = ButtonType.MULTIPLY),
                CalculatorButton(text = "4", color = ButtonColor.NORMAL,type = ButtonType.NUMBER),
                CalculatorButton(text = "5", color = ButtonColor.NORMAL,type = ButtonType.NUMBER),
                CalculatorButton(text = "6", color = ButtonColor.NORMAL,type = ButtonType.NUMBER),
                CalculatorButton(text = "-", color = ButtonColor.OPERATION,type = ButtonType.SUBTRACT),
                CalculatorButton(text = "1", color = ButtonColor.NORMAL,type = ButtonType.NUMBER),
                CalculatorButton(text = "2", color = ButtonColor.NORMAL,type = ButtonType.NUMBER),
                CalculatorButton(text = "3", color = ButtonColor.NORMAL,type = ButtonType.NUMBER),
                CalculatorButton(text = "+", color = ButtonColor.OPERATION,type = ButtonType.ADD),
                CalculatorButton(icon = Icons.Outlined.Assignment, color = ButtonColor.NORMAL,type = ButtonType.CLIPBOARD),
                CalculatorButton(text = "0", color = ButtonColor.NORMAL,type = ButtonType.NUMBER),
                CalculatorButton(text = ".", color = ButtonColor.NORMAL, type = ButtonType.DECIMAL),
                CalculatorButton(text = "=", color = ButtonColor.OPERATION,type = ButtonType.CALCULATE),
            )
        )
    }

    //Calculator Buttons
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column {
            Text(
                text = state.firstNumber + (state.operation?.symbol ?: "") + state.secondNumber,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 72.sp,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                textAlign = TextAlign.End
            )
            LazyVerticalGrid(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(8.dp),
                columns = GridCells.Fixed(4),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(calculatorButtons) { button ->
                    CalculatorButton(button = button, onClick =  onAction )
                }
            }
        }
    }


    //calculator toggle for theme changing
    CalculatorThemeToggle(themeViewModel)
}

// Ui for a single button
@Composable
fun CalculatorButton(
    button: CalculatorButton,
    onClick: (CalculatorAction) -> Unit,
    modifier: Modifier = Modifier
) {

    val contentColor = when (button.color) {
        ButtonColor.NORMAL -> MaterialTheme.colorScheme.onBackground
        ButtonColor.OPERATION -> Orange
        ButtonColor.ACTION -> Cyan
    }

    val buttonType:CalculatorAction = when(button.type){
        ButtonType.NUMBER -> CalculatorAction.Number(button.text!!.toInt())
        ButtonType.DECIMAL -> CalculatorAction.Decimal
        ButtonType.CALCULATE -> CalculatorAction.Calculate
        ButtonType.RESET -> CalculatorAction.Reset
        ButtonType.DELETE -> CalculatorAction.Delete
        ButtonType.CLIPBOARD -> CalculatorAction.ClipBoard
        ButtonType.ADD -> CalculatorAction.Operation(CalculatorOperation.Add)
        ButtonType.SUBTRACT -> CalculatorAction.Operation(CalculatorOperation.Subtract)
        ButtonType.MULTIPLY -> CalculatorAction.Operation(CalculatorOperation.Multiply)
        ButtonType.DIVIDE -> CalculatorAction.Operation(CalculatorOperation.Divide)
        ButtonType.MODULES -> CalculatorAction.Operation(CalculatorOperation.Modules)
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxHeight()
            .aspectRatio(1f)
            .clickable {
                onClick(buttonType)
            }
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        if (button.text != null) {
            Text(
                text = button.text,
                color = contentColor,
                fontSize = 28.sp
            )
        } else {
            Icon(
                imageVector = button.icon!!,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

