package com.abra.calculator.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.abra.calculator.viewModel.ThemeViewModel

//Calculator Toggle for theme changing

@Composable
fun CalculatorThemeToggle(
    themeViewModel: ThemeViewModel,
    modifier: Modifier = Modifier
) {
    val darkModeEnabled by themeViewModel.darkMode.collectAsState()
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.secondary)
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(20.dp)
                    .clickable {
                        if (!darkModeEnabled) {
                            themeViewModel.toggleTheme()
                        }
                    },
                imageVector = Icons.Outlined.DarkMode,
                contentDescription = "Dark Mode",
                tint = if (darkModeEnabled) {
                    MaterialTheme.colorScheme.onBackground.copy(alpha = .5f)
                }else{
                    MaterialTheme.colorScheme.onBackground
                }
            )
            Divider(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .height(20.dp)
                    .width(1.dp),
                color = MaterialTheme.colorScheme.primary
            )
            Icon(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(20.dp)
                    .clickable {
                        if (darkModeEnabled) {
                            themeViewModel.toggleTheme()
                        }
                    },
                imageVector = Icons.Outlined.LightMode,
                contentDescription = "Light Mode",
                tint = if (!darkModeEnabled) {
                    MaterialTheme.colorScheme.onBackground.copy(alpha = .5f)
                }else{
                    MaterialTheme.colorScheme.onBackground
                }
            )
        }
    }
}
