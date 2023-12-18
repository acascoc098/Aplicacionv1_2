package com.example.aplicacionv1_2.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ViewBar (view: View,
               var deleteOnClick: (Int) -> Unit,
               var updateOnClick: (Int) -> Unit):
    RecyclerView.ViewHolder (view){

        var binding
}