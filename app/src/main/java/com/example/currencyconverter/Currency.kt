package com.example.currencyconverter

class Currency(private val valueToBaseCurrency: Float, val name: String, val img: Int) { // Main class describes currency object
    constructor() : this(0f, "", 0) // default constructor

    fun convertTo(currency: Currency, withAmount: Float? = null): Float {
        val ratio = currency.valueToBaseCurrency / this.valueToBaseCurrency

        return if (withAmount == null) ratio else ratio * withAmount
    }

    override fun toString(): String = "$name $valueToBaseCurrency"
}

val dollarCurrency = Currency( 1f, "USD", R.drawable.usd)
val euroCurrency = Currency(0.88f, "EUR", R.drawable.eur)
val rubleCurrency = Currency(74f, "RUS", R.drawable.rub)
val poundCurrency = Currency(0.78f, "GBP", R.drawable.gbp)
val yuanCurrency = Currency(7.07f, "CNY", R.drawable.cny)

val currencyList = listOf(
    dollarCurrency,
    rubleCurrency,
    euroCurrency,
    poundCurrency,
    yuanCurrency
)