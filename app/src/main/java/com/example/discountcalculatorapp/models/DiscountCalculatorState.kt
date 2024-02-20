package com.example.discountcalculatorapp.models

data class DiscountCalculatorState(
    val price: String = "",
    val discount: String = "",
    val finalPrice: Double= 0.0,
    val discountedAmount: Double = 0.0,
    val isValidPrice: Boolean = true,
    val isValidDiscount: Boolean = true
)