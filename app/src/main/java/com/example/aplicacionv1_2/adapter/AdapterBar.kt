package com.example.aplicacionv1_2.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionv1_2.models.Bar

class AdapterBar (var listBar : MutableList<Bar>, var deleteOnClick: (Int) -> Unit, var updateOnClick: (Int) -> Unit) : RecyclerView.Adapter<ViewBar> {
}