package com.example.tcmbexchangeratedata


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CurrencyAdapter(private val currencyList: List<Currency>) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardtasarim, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencyList[position]
        holder.unit.text = currency.unit
        holder.currency.text = currency.currencyName
        holder.forexBuying.text = "Buying: ${currency.forexBuying}"
        holder.forexSelling.text = "Selling: ${currency.forexSelling}"
        //holder.icon.setImageResource(R.drawable.ic_currency_icon)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val unit: TextView = itemView.findViewById(R.id.Unit)
        val currency: TextView = itemView.findViewById(R.id.Currency)
        val forexBuying: TextView = itemView.findViewById(R.id.Buying)
        val forexSelling: TextView = itemView.findViewById(R.id.Selling)
        // val icon: ImageView = itemView.findViewById(R.id.icon)
    }
}