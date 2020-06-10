package com.example.currencyconverter

class Currency(
    private val ratioToBaseCurrency: Float, // Base currency is dollar
    val code: String,
    val img: Int
) {

    fun convertTo(currency: Currency, withAmount: Float? = null): Float {
        val ratio = currency.ratioToBaseCurrency / this.ratioToBaseCurrency

        return if (withAmount == null) ratio else ratio * withAmount
    }

    override fun toString(): String = "$code $ratioToBaseCurrency"
}

enum class CURRENCIES(val currency: Currency) {
    DOLLAR(Currency(1f, "USD", R.drawable.usd)),
    EURO(Currency(0.88f, "EUR", R.drawable.eur)),
    RUBLE(Currency(74f, "RUS", R.drawable.rub)),
    POUND(Currency(0.78f, "GBP", R.drawable.gbp)),
    CANADIAN_DOLLAR(Currency(1.33f, "CAD", R.drawable.cad)),
    YUAN(Currency(7.07f, "CNY", R.drawable.cny)),
    LEV(Currency(1.73f, "BGN", R.drawable.bgn)),
    BITCOIN(Currency(0.0001f, "BTC", R.drawable.btc))
}

fun currenciesList(): List<Currency> = CURRENCIES.values().map { it.currency }