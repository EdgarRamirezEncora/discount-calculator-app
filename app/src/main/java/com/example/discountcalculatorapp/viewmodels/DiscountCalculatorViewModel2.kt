package com.example.discountcalculatorapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DiscountCalculatorViewModel2 : ViewModel() {
    var price by mutableStateOf("")
        private set
    var discount by mutableStateOf("")
        private set
    var finalPrice by mutableStateOf(0.0)
        private set
    var discountedAmount by mutableStateOf(0.0)
        private set
    var isValidPrice by mutableStateOf(true)
        private set
    var isValidDiscount by mutableStateOf(true)
        private set

    fun onValue(value: String, field: String) {
        when(field) {
            "price" -> price = value
            "discount" -> discount = value
        }
    }

    fun calculate(): Unit {
        isValidPrice = validateNumber(price)
        isValidDiscount = validateNumber(discount)

        if (isValidPrice && isValidDiscount) {
            finalPrice = calculateFinalPrice(price = price.toDouble(), discount = discount.toDouble())
            discountedAmount = calculateDiscount(price = price.toDouble(), discount = discount.toDouble())
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

    fun clear() {
        price = ""
        discount = ""
        finalPrice = 0.0
        discountedAmount = 0.0
    }

    fun closeAlert(field: String) {
        when(field) {
            "price" -> isValidPrice = true
            "discount" -> isValidDiscount = true
        }
    }
}