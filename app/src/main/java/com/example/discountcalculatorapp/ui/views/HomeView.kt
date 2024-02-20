package com.example.discountcalculatorapp.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.discountcalculatorapp.dataStore.DiscountCalculatorDataStore
import com.example.discountcalculatorapp.ui.components.Alert
import com.example.discountcalculatorapp.ui.components.MainButton
import com.example.discountcalculatorapp.ui.components.MainTextField
import com.example.discountcalculatorapp.ui.components.SpacerH
import com.example.discountcalculatorapp.ui.components.SpacerV
import com.example.discountcalculatorapp.ui.components.TitleBar
import com.example.discountcalculatorapp.ui.components.TwoCards
import com.example.discountcalculatorapp.viewmodels.DiscountCalculatorViewModel1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(dataStore: DiscountCalculatorDataStore, viewModel1: DiscountCalculatorViewModel1, isDarkMode: Boolean) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TitleBar(name = "Discount Calculator App", dataStore = dataStore, isDarkMode = isDarkMode) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Red,
                )
            )
        }
    ) {
        HomeViewContent(it, viewModel1)
    }
}

@Composable
fun HomeViewContent(paddingValues: PaddingValues, viewModel1: DiscountCalculatorViewModel1) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(10.dp),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var price by remember { mutableStateOf("") }
        var discount by remember{ mutableStateOf("") }
        var finalPrice by remember { mutableDoubleStateOf(0.0) }
        var discountedAmount by remember{ mutableDoubleStateOf(0.0) }
        var isValidPrice by remember { mutableStateOf(true) }
        var isValidDiscount by remember { mutableStateOf(true) }


        TwoCards(
            title1 = "Final Price",
            number1 = finalPrice,
            title2 = "Discounted",
            number2 = discountedAmount
        )
        SpacerV()
        MainTextField(value = price, onValueChange = { price = it }, label = "Price")
        SpacerH()
        MainTextField(value = discount, onValueChange = { discount = it }, label = "Discount %")
        SpacerV(20.dp)
        MainButton(
            text = "Generate discount",
            onClick = {
                val result = viewModel1.calculate(price, discount)
                isValidPrice = result.second.first
                isValidDiscount = result.second.second
                finalPrice = result.first.first
                discountedAmount = result.first.second
            }
        )
        SpacerV()
        MainButton(text = "Clear", onClick = {
            price = ""
            discount = ""
            finalPrice = 0.0
            discountedAmount = 0.0
        }, color = Color.Red)

        if (!isValidPrice) {
          Alert(
                title = "Invalid Price",
                message = viewModel1.getAlertMessage("price", price),
                confirmText = "Ok",
                onConfirmClick = { isValidPrice = true }
          ) {}
        }

        if (!isValidDiscount) {
            Alert(
                title = "Invalid Discount",
                message = viewModel1.getAlertMessage("discount", discount),
                confirmText = "Ok",
                onConfirmClick = { isValidDiscount = true }
            ) {}
        }
    }
}