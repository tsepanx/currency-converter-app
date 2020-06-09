package com.example.currencyconverter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.currencyconverter.dummy.DummyContent.DummyItem
import java.util.logging.Logger

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

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            view.setOnClickListener(this)
        }

        val idView: TextView = view.findViewById(R.id.id)
        val nameView: TextView = view.findViewById(R.id.name)

        override fun onClick(v: View?) {
            Logger.getLogger("recycler view").warning(this.layoutPosition.toString())
        }

        override fun toString(): String {
            return super.toString() + " ${nameView.text}"
        }
    }
}