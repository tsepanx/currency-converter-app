package com.example.currencyconverter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class MyCurrencyAdapter(private val values: List<Currency>, val activity: CurrencyListActivity) : RecyclerView.Adapter<MyCurrencyAdapter.ViewHolder>() {

    override fun getItemCount(): Int = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.nameView.text = item.name
        holder.imgView.setImageResource(item.img)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            view.setOnClickListener(this)
        }

        val nameView: TextView = view.findViewById(R.id.name)
        val imgView: ImageView = view.findViewById(R.id.flag_img)

        private val currency: Currency
            get() = this@MyCurrencyAdapter.values[this.layoutPosition]

        override fun onClick(v: View?) {
            selectedCurrency = this.currency
            activity.onBackPressed()
        }

        override fun toString(): String {
            return super.toString() + " ${nameView.text}"
        }
    }
}