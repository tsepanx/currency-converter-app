package com.example.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.dummy.DummyContent
import java.util.logging.Logger

const val baseCurrencyName = "DOLLAR"

class Currency(val id: Int, private var valueToBaseCurrency: Float, var name: String) {
    fun convertTo(currency: Currency): Float {
        return this.valueToBaseCurrency / currency.valueToBaseCurrency
    }
}

class MainActivity : AppCompatActivity() {
//    private lateinit var firstField: TextView
//    private lateinit var convertButton: Button
//
//    private lateinit var lastEditedField: TextView
//
//    private lateinit var currencyMap: Map<TextView, Currenc
//    private lateinit var secondField: TextViewy>

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        firstField = findViewById(R.id.field1)
//        secondField = findViewById(R.id.field2)

//        currencyMap = mapOf(
//            firstField to Currency(1, 74f, "RUB"),
//            secondField to Currency(2, 0.9f, "EUR")
//        )

//        convertButton = findViewById(R.id.convertBtn)
//
//        lastEditedField = firstField

//        val defaultTextWatcher = { view: TextView ->  object : TextWatcher {
//            override fun afterTextChanged(p0: Editable?) {
//                lastEditedField = view
//            }
//
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//        }}

//        firstField.addTextChangedListener(defaultTextWatcher(firstField))
//        secondField.addTextChangedListener(defaultTextWatcher(secondField))

        val myDataset = listOf(
            DummyContent.DummyItem("1", "RUB", ""),
            DummyContent.DummyItem("2", "USD", ""),
            DummyContent.DummyItem("3", "EUR", "")
        )

        val viewManager = LinearLayoutManager(this)
        val viewAdapter = MyCurrencyAdapter(myDataset)


        recyclerView = findViewById<RecyclerView>(R.id.main_list).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

    fun onConvert(view: View) {
        val a = recyclerView.findViewHolderForAdapterPosition(1)
        val b = a as MyCurrencyAdapter.ViewHolder
        Logger.getLogger("list").warning(b.nameView.text.toString())

//        val lastEditedText = lastEditedField.text.toString()
//
//        Logger.getLogger("convert").warning(lastEditedText)
//
////        secondField.text = firstField.text
    }
}