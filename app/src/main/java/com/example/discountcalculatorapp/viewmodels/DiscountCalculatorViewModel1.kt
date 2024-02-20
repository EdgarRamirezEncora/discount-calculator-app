package com.example.discountcalculatorapp.viewmodels

import androidx.lifecycle.ViewModel

class DiscountCalculatorViewModel1: ViewModel() {
    // ViewModel using parameters from the composable

    fun calculate(price: String, discount: String): Pair<Pair<Double, Double>, Pair<Boolean, Boolean>> {
        var discountedAmount = 0.0
        var finalPrice = 0.0
        val isValidPrice: Boolean = validateNumber(price)
        val isValidDiscount: Boolean = validateNumber(discount)

        if (isValidPrice && isValidDiscount) {
            finalPrice = calculateFinalPrice(price = price.toDouble(), discount = discount.toDouble())
            discountedAmount = calculateDiscount(price = price.toDouble(), discount = discount.toDouble())
        }

        return Pair(Pair(finalPrice, discountedAmount), Pair(isValidPrice, isValidDiscount))
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