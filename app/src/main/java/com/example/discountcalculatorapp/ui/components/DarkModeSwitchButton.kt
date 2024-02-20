package com.example.discountcalculatorapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.discountcalculatorapp.R
import com.example.discountcalculatorapp.dataStore.DiscountCalculatorDataStore
import kotlinx.coroutines.launch

@Composable
fun DarkModeSwitchButton (dataStore: DiscountCalculatorDataStore, isDarkMode: Boolean) {
    val scope = rememberCoroutineScope()

    Switch(
        checked = isDarkMode,
        onCheckedChange = {
            scope.launch {
                dataStore.saveDarkMode(it)
            }
        },
        thumbContent = if (isDarkMode) {
            {
                Icon(
                    painter = painterResource(id = R.drawable.light_mode_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(SwitchDefaults.IconSize)
                )
            }
        } else {
            {
                Icon(
                    painter = painterResource(id = R.drawable.dark_mode_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(SwitchDefaults.IconSize)
                )
            }
        }
    )
}