package com.example.currencyconverter

class Currency(
    private val ratioToBaseCurrency: Float, // Base currency is dollar
    val name: String,
    val img: Int
) {

    fun convertTo(currency: Currency, withAmount: Float? = null): Float {
        val ratio = currency.ratioToBaseCurrency / this.ratioToBaseCurrency

        return if (withAmount == null) ratio else ratio * withAmount
    }

    override fun toString(): String = "$name $ratioToBaseCurrency"
}

enum class CURRENCIES(val currency: Currency) {
    DOLLAR(Currency(1f, "USD", R.drawable.usd)),
    EURO(Currency(0.88f, "EUR", R.drawable.eur)),
    RUBLE(Currency(74f, "RUS", R.drawable.rub)),
    POUND(Currency(0.78f, "GBP", R.drawable.gbp)),
    YUAN(Currency(7.07f, "CNY", R.drawable.cny));
}

fun currenciesList(): List<Currency> = CURRENCIES.values().map { it.currency }