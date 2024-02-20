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
import com.example.discountcalculatorapp.viewmodels.DiscountCalculatorViewModel3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView3(dataStore: DiscountCalculatorDataStore, viewModel3: DiscountCalculatorViewModel3, isDarkMode: Boolean) {
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
        HomeViewContent(it, viewModel3)
    }
}

@Composable
fun HomeViewContent(paddingValues: PaddingValues, viewModel3: DiscountCalculatorViewModel3) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(10.dp),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state = viewModel3.state
        TwoCards(
            title1 = "Final Price",
            number1 = state.finalPrice,
            title2 = "Discounted",
            number2 = state.discountedAmount
        )
        SpacerV()
        MainTextField(value = state.price, onValueChange = { viewModel3.onValue(it, "price") }, label = "Price")
        SpacerH()
        MainTextField(value = state.discount, onValueChange = { viewModel3.onValue(it, "discount") }, label = "Discount %")
        SpacerV(20.dp)
        MainButton(
            text = "Generate discount",
            onClick = { viewModel3.calculate() }
        )
        SpacerV()
        MainButton(text = "Clear", onClick = { viewModel3.clear() }, color = Color.Red)

        if (!viewModel3.state.isValidPrice) {
            Alert(
                title = "Invalid Price",
                message = viewModel3.getAlertMessage("price", viewModel3.state.price),
                confirmText = "Ok",
                onConfirmClick = { viewModel3.closeAlert("price") }
            ) {}
        }

        if (!viewModel3.state.isValidDiscount) {
            Alert(
                title = "Invalid Discount",
                message = viewModel3.getAlertMessage("discount", viewModel3.state.discount),
                confirmText = "Ok",
                onConfirmClick = { viewModel3.closeAlert("discount") }
            ) {}
        }
    }
}