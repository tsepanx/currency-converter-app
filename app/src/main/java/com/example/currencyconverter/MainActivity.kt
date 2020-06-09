package com.example.currencyconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.logging.Logger
import kotlin.math.round


var selectedCurrency: Currency? = null

fun Float.round(decimals: Int): Float {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return (round(this * multiplier) / multiplier).toFloat()
}


class Currency(private val valueToBaseCurrency: Float, val name: String, val img: Int) {
    constructor() : this(0f, "", 0)

    fun convertTo(currency: Currency): Float {
        return currency.valueToBaseCurrency / this.valueToBaseCurrency
    }

    override fun toString(): String = "$name $valueToBaseCurrency"
}

val dollarCurrency = Currency( 1f, "USD", R.drawable.usd)
val rusCurrency = Currency(74f, "RUS", R.drawable.rub)
val euroCurrency = Currency(0.88f, "EUR", R.drawable.eur)

val currencyList = listOf(
    dollarCurrency,
    rusCurrency,
    euroCurrency
)

class MainActivity : AppCompatActivity() {
    private lateinit var inputView: TextView
    private lateinit var resultView: TextView

    private lateinit var ratioView: TextView

    private lateinit var convertButton: Button

    private lateinit var leftImageView: ImageView
    private lateinit var rightImageView: ImageView

    private var isLeftCurrencySelected: Boolean? = null

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

        if (isLeftCurrencySelected != null && selectedCurrency != null) {
            if (isLeftCurrencySelected!!)
                fromCurrency = selectedCurrency!!
            else
                toCurrency = selectedCurrency!!
        }


        this.setRatioView()
        this.convert(0f)
    }

    private fun setRatioView() {
        var ratio = fromCurrency.convertTo(toCurrency)
        ratio = ratio.round(4)

        ratioView.text = "1 ${fromCurrency.name} -> $ratio ${toCurrency.name}"
    }

    private fun convert(amount: Float?) {
        if (amount != null) {
            val amountToValue = fromCurrency.convertTo(toCurrency) * amount
            Logger.getLogger("onConvert").warning("$amount $amountToValue")

            this.resultView.text = amountToValue.toString()
        } else {
            resultView.text = 0f.toString()
        }
    }

    fun onFlagClick(view: View) {
        this.isLeftCurrencySelected = view == leftImageView
        val intent = Intent(this, CurrencyListActivity::class.java)

        startActivity(intent)
    }

    fun onConvertClick(view: View) {
        val amountFromValue = this.inputView.text.toString().toFloatOrNull()
        convert(amountFromValue)
    }
}