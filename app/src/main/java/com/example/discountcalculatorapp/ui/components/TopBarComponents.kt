package com.example.discountcalculatorapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.discountcalculatorapp.dataStore.DiscountCalculatorDataStore

@Composable
fun TitleBar(
    name: String,
    dataStore: DiscountCalculatorDataStore,
    isDarkMode: Boolean) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            fontSize = 25.sp,
            color = Color.White,
        )
        DarkModeSwitchButton(dataStore = dataStore, isDarkMode = isDarkMode)
    }
}