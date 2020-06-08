package com.example.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {
    private lateinit var firstField: TextView
    private lateinit var secondField: TextView
    private lateinit var convertButton: Button

    private lateinit var lastEditedField: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstField = findViewById(R.id.field1)
        secondField = findViewById(R.id.field2)

        convertButton = findViewById(R.id.convertBtn)

        lastEditedField = firstField

        val defaultTextWatcher = { view: TextView ->  object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                lastEditedField = view
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        }}

        firstField.addTextChangedListener(defaultTextWatcher(firstField))
        secondField.addTextChangedListener(defaultTextWatcher(secondField))
    }

    fun onFieldClick(view: View) {
//        val textView = view as TextView
        Logger.getLogger("fdf").warning("Clicked ")
//        view.text = "clicked"
    }

    fun onConvert(view: View) {
        secondField.text = firstField.text
    }
}