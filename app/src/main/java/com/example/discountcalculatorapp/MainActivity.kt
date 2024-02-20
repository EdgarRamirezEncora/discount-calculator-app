package com.example.discountcalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.discountcalculatorapp.dataStore.DiscountCalculatorDataStore
import com.example.discountcalculatorapp.ui.views.HomeView
import com.example.discountcalculatorapp.ui.theme.DiscountCalculatorAppTheme
import com.example.discountcalculatorapp.ui.views.HomeView2
import com.example.discountcalculatorapp.ui.views.HomeView3
import com.example.discountcalculatorapp.viewmodels.DiscountCalculatorViewModel1
import com.example.discountcalculatorapp.viewmodels.DiscountCalculatorViewModel2
import com.example.discountcalculatorapp.viewmodels.DiscountCalculatorViewModel3
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val dataStore = DiscountCalculatorDataStore(this)
            val darkMode = dataStore.getDarkMode.collectAsState(initial = false)

            val viewModel: DiscountCalculatorViewModel2 by viewModels()

            DiscountCalculatorAppTheme(darkTheme = darkMode.value) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    HomeView2(dataStore = dataStore, viewModel2 = viewModel, isDarkMode = darkMode.value)
                }
            }
        }
    }
}