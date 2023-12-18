package com.example.aplicacionv1_2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionv1_2.R
import com.example.aplicacionv1_2.models.Bar

class AdapterBar (var listBar : MutableList<Bar>,
                  var deleteOnClick: (Int) -> Unit,
                  var updateOnClick: (Int) -> Unit): RecyclerView.Adapter<ViewBar>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBar {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutitemBar = R.layout.item_bar
        return ViewBar(layoutInflater.inflate(layoutitemBar,parent,false),deleteOnClick,updateOnClick)
    }

    override fun onBindViewHolder(holder: ViewBar, position: Int) {
        holder.renderize(listBar.get(position),position)
    }

    override fun getItemCount(): Int = listBar.size
}