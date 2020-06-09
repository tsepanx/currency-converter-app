package com.example.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.dummy.DummyContent
import java.util.logging.Logger

class CurrencyListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_list)

        val myDataset = listOf(
            DummyContent.DummyItem("1", "AAA", ""),
            DummyContent.DummyItem("1", "AAA", ""),
            DummyContent.DummyItem("1", "AAA", ""),
            DummyContent.DummyItem("1", "AAA", ""),
            DummyContent.DummyItem("1", "AAA", ""),
            DummyContent.DummyItem("1", "AAA", ""),
            DummyContent.DummyItem("1", "AAA", ""),
            DummyContent.DummyItem("1", "AAA", "")
        )

        val viewManager = LinearLayoutManager(this)
        val viewAdapter = MyCurrencyAdapter(myDataset)

        recyclerView = findViewById<RecyclerView>(R.id.list).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}