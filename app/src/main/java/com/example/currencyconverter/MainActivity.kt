package com.example.currencyconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.logging.Logger
import kotlin.math.round

fun Float.round(decimals: Int): Float {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return (round(this * multiplier) / multiplier).toFloat()
}


class Currency(val id: Int, private val valueToBaseCurrency: Float, val name: String, val img: Int) {
    constructor() : this(0, 0f, "", 0)

    fun convertTo(currency: Currency): Float {
        return currency.valueToBaseCurrency / this.valueToBaseCurrency
    }
}

val dollarCurrency = Currency(0, 1f, "USD", R.drawable.usd)
val rusCurrency = Currency(1, 74f, "RUS", R.drawable.rub)
val euroCurrency = Currency(2, 0.88f, "EUR", R.drawable.eur)

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

    private lateinit var fromImageView: ImageView
    private lateinit var toImageView: ImageView

    private var fromCurrency: Currency = Currency()
        set(value) {
            fromImageView.setImageResource(value.img)
            inputView.hint = value.name

            field = value
        }

    private var toCurrency: Currency = Currency()
        set(value) {
            field = value
            toImageView.setImageResource(value.img)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputView = findViewById(R.id.input_view)
        resultView = findViewById(R.id.result_view)

        ratioView = findViewById(R.id.currency_ratio)

        convertButton = findViewById(R.id.convert_btn)

        fromImageView = findViewById(R.id.from_img)
        toImageView = findViewById(R.id.to_img)

        fromCurrency = dollarCurrency
        toCurrency = euroCurrency
    }

    override fun onStart() {
        super.onStart()

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
        val currency: Currency = if (view == fromImageView) fromCurrency else toCurrency
        val currencyId = currency.id

        Logger.getLogger("flag click").warning(currencyId.toString())

        val intent = Intent(this, CurrencyListActivity::class.java)
        intent.putExtra("id", currencyId)

        startActivity(intent)
    }

    fun onConvertClick(view: View) {
        val amountFromValue = this.inputView.text.toString().toFloatOrNull()
        convert(amountFromValue)
    }
}