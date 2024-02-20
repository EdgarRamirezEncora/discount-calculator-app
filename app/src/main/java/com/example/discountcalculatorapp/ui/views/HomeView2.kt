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
import com.example.discountcalculatorapp.viewmodels.DiscountCalculatorViewModel2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView2(
    dataStore: DiscountCalculatorDataStore,
    viewModel2: DiscountCalculatorViewModel2,
    isDarkMode: Boolean) {
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
        HomeViewContent(it, viewModel2)
    }
}

@Composable
fun HomeViewContent(paddingValues: PaddingValues, viewModel2: DiscountCalculatorViewModel2) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(10.dp),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TwoCards(
            title1 = "Final Price",
            number1 = viewModel2.finalPrice,
            title2 = "Discounted",
            number2 = viewModel2.discountedAmount
        )
        SpacerV()
        MainTextField(value = viewModel2.price, onValueChange = { viewModel2.onValue(it, "price") }, label = "Price")
        SpacerH()
        MainTextField(value = viewModel2.discount, onValueChange = { viewModel2.onValue(it, "discount") }, label = "Discount %")
        SpacerV(20.dp)
        MainButton(
            text = "Generate discount",
            onClick = { viewModel2.calculate() }
        )
        SpacerV()
        MainButton(text = "Clear", onClick = { viewModel2.clear() }, color = Color.Red)

        if (!viewModel2.isValidPrice) {
            Alert(
                title = "Invalid Price",
                message = viewModel2.getAlertMessage("price", viewModel2.price),
                confirmText = "Ok",
                onConfirmClick = { viewModel2.closeAlert("price") }
            ) {}
        }

        if (!viewModel2.isValidDiscount) {
            Alert(
                title = "Invalid Discount",
                message = viewModel2.getAlertMessage("discount", viewModel2.discount),
                confirmText = "Ok",
                onConfirmClick = { viewModel2.closeAlert("discount") }
            ) {}
        }
    }
}