package com.example.discountcalculatorapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.discountcalculatorapp.models.DiscountCalculatorState

class DiscountCalculatorViewModel3: ViewModel() {

    var state by mutableStateOf(DiscountCalculatorState())
        private set

    fun onValue(value: String, text: String) {
        when(text) {
            "price" -> state = state.copy(price = value)
            "discount" -> state = state.copy(discount = value)
        }
    }

    fun clear() {
        state = state.copy(
            price = "",
            discount = "",
            finalPrice = 0.0,
            discountedAmount = 0.0
        )
    }

    fun closeAlert(field: String) {
        when(field) {
            "price" -> state = state.copy(isValidPrice = true)
            "discount" -> state = state.copy(isValidDiscount = true)
        }
    }

    fun calculate(): Unit {
        state = state.copy(
            isValidPrice = validateNumber(state.price),
            isValidDiscount = validateNumber(state.discount)
        )

        if (state.isValidPrice && state.isValidDiscount) {
            state = state.copy(
                finalPrice = calculateFinalPrice(
                    price = state.price.toDouble(),
                    discount = state.discount.toDouble()
                ),
                discountedAmount = calculateDiscount(
                    price = state.price.toDouble(),
                    discount = state.discount.toDouble()
                )
            )
        }
    }

    private fun calculateFinalPrice(price: Double, discount: Double) : Double = price - calculateDiscount(price, discount)
    private fun calculateDiscount(price: Double, discount: Double) : Double = price * (discount/100)

    private fun validateNumber(price: String): Boolean {
        val numberRegex = Regex("^\\d+(.\\d+)?$")
        return numberRegex.matches(price)
    }

    fun getAlertMessage(field: String, fieldValue: String) : String = if(fieldValue.isNotEmpty()) {"The $field must be a number."}
    else {"The $field is required."}
}