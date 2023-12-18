package com.example.aplicacionv1_2.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacionv1_2.databinding.ItemBarBinding
import com.example.aplicacionv1_2.models.Bar

class ViewBar (view: View,
               var deleteOnClick: (Int) -> Unit,
               var updateOnClick: (Int) -> Unit):
    RecyclerView.ViewHolder (view){

        var binding: ItemBarBinding
        init {
            binding = ItemBarBinding.bind(view)
        }
    fun renderize(bar: Bar, position: Int){
        binding.txtviewName.setText(bar.name)
        binding.txtviewCity.setText(bar.city)
        binding.txtviewProvince.setText(bar.province)
        binding.txtviewPhone.setText(bar.phone)
        Glide
            .with( itemView.context)
            .load(bar. image)
            .centerCrop()
            .into( binding.ivBar)
        setOnClickListener(position)
    }

    private fun setOnClickListener(position: Int) {
        binding.btnEdit.setOnClickListener {
            updateOnClick(position )
        }
        binding.btnDelete.setOnClickListener {
            deleteOnClick(position)
        }
    }
}