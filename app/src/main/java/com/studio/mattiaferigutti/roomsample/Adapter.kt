package com.studio.mattiaferigutti.roomsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//todo: if the name is the same the user has to change it

class   Adapter(private val list: MutableList<Element?>?) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val text: TextView = item.findViewById<TextView>(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentElement = list?.get(position)
        holder.text.text = currentElement?.elementName
    }

    fun addInList(element: Element) {
        list?.add(element)
        notifyDataSetChanged()
    }
}