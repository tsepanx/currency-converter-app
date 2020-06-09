package com.example.currencyconverter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.currencyconverter.dummy.DummyContent.DummyItem

var inputList: MutableList<TextView> = mutableListOf()

class MyCurrencyAdapter(private val values: List<DummyItem>) : RecyclerView.Adapter<MyCurrencyAdapter.ViewHolder>() {

    override fun getItemCount(): Int = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.nameView.text = item.content
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_id)
        val nameView: TextView = view.findViewById(R.id.name)
        private val inputView: TextView = view.findViewById(R.id.input)

        init {
            inputList.add(this.inputView)
        }

        override fun toString(): String {
            return super.toString() + " ${nameView.text} ${inputView.text}"
        }
    }
}