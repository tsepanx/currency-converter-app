package com.example.currencyconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.round

fun Float.round(decimals: Int): Float {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return (round(this * multiplier) / multiplier).toFloat()
}

class Currency(private val valueToBaseCurrency: Float, val name: String, val img: Int) { // Main class describes currency object
    constructor() : this(0f, "", 0) // default constructor

    fun convertTo(currency: Currency, withAmount: Float? = null): Float {
        val ratio = currency.valueToBaseCurrency / this.valueToBaseCurrency

        return if (withAmount == null) ratio else ratio * withAmount
    }

    override fun toString(): String = "$name $valueToBaseCurrency"
}

val dollarCurrency = Currency( 1f, "USD", R.drawable.usd)
val rubleCurrency = Currency(74f, "RUS", R.drawable.rub)
val euroCurrency = Currency(0.88f, "EUR", R.drawable.eur)
val poundCurrency = Currency(0.78f, "GBP", R.drawable.gbp)
val yuanCurrency = Currency(7.07f, "CNY", R.drawable.cny)

val currencyList = listOf(
    dollarCurrency,
    rubleCurrency,
    euroCurrency,
    poundCurrency,
    yuanCurrency
)

class MainActivity : AppCompatActivity() {
    private lateinit var inputView: TextView
    private lateinit var resultView: TextView

    private lateinit var ratioView: TextView

    private lateinit var convertButton: Button

    private lateinit var leftImageView: ImageView
    private lateinit var rightImageView: ImageView

    private var isLeftFlagClicked: Boolean? = null // left or right currency that user is going to change

    private var fromCurrency: Currency = Currency() // displayed as left currency
        set(value) {
            leftImageView.setImageResource(value.img)
            inputView.hint = value.name

            field = value
        }

    private var toCurrency: Currency = Currency() // displayed as right currency
        set(value) {
            rightImageView.setImageResource(value.img)

            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputView = findViewById(R.id.input_view)
        resultView = findViewById(R.id.result_view)

        ratioView = findViewById(R.id.currency_ratio)

        convertButton = findViewById(R.id.convert_btn)

        leftImageView = findViewById(R.id.left_img)
        rightImageView = findViewById(R.id.right_img)

        fromCurrency = dollarCurrency // TODO Hardcoded values
        toCurrency = euroCurrency
    }

    override fun onResume() {
        super.onResume()

        if (isLeftFlagClicked != null && selectedCurrency != null) {
            if (isLeftFlagClicked!!)
                fromCurrency = selectedCurrency!!
            else
                toCurrency = selectedCurrency!!
        }


        this.setRatioView()
        this.onConvert()
    }

    private fun setRatioView() {
        var ratio = fromCurrency.convertTo(toCurrency)
        ratio = ratio.round(4)

        ratioView.text = "1 ${fromCurrency.name} -> $ratio ${toCurrency.name}"
    }

    private fun convertCurrentCurrencies(amount: Float?) {
        if (amount != null) {
            val resultOfConvert = fromCurrency.convertTo(toCurrency, amount)
            this.resultView.text = resultOfConvert.toString()
        } else {
            resultView.text = 0f.toString()
        }
    }

    fun onFlagClick(view: View) {
        this.isLeftFlagClicked = view == leftImageView

        startActivity(Intent(this, CurrencyListActivity::class.java))
    }

    private fun onConvert() {
        val inputValue = inputView.text.toString().toFloatOrNull()
        convertCurrentCurrencies(inputValue)
    }

    fun onConvertButtonClick(view: View) = onConvert()
}