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

fun ratioText(ratio: Float, fromCode: String, toCode: String) = "1 $fromCode -> $ratio $toCode"

class MainActivity : AppCompatActivity() {
    private lateinit var inputView: TextView
    private lateinit var resultView: TextView

    private lateinit var ratioView: TextView

    private lateinit var convertButton: Button

    private lateinit var leftImageView: ImageView
    private lateinit var rightImageView: ImageView

    private var isLeftFlagClicked: Boolean? =
        null // left or right currency that user is going to change

    private var fromCurrency: Currency = Currency() // displayed as left currency
        set(value) {
            leftImageView.setImageResource(value.img)
            inputView.hint = value.code

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
    }

    override fun onStart() {
        super.onStart()

        this.fromCurrency = CURRENCIES.EURO.currency
        this.toCurrency = CURRENCIES.RUBLE.currency
    }

    override fun onResume() {
        super.onResume()

        if (isLeftFlagClicked != null) {
            if (selectedCurrency != null) {
                if (isLeftFlagClicked == true)
                    fromCurrency = selectedCurrency!! else toCurrency = selectedCurrency!!

                selectedCurrency = null
            }
        }

        this.setRatioView()
        this.onConvert()
    }

    private fun setRatioView() {
        var ratio = fromCurrency.convertTo(toCurrency)
        ratio = ratio.round(5)

        ratioView.text = ratioText(ratio, fromCurrency.code, toCurrency.code)
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