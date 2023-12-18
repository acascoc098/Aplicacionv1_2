package com.example.aplicacionv1_2.controller

import android.app.Activity
import android.content.Context
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacionv1_2.MainActivity
import com.example.aplicacionv1_2.R
import com.example.aplicacionv1_2.adapter.AdapterBar
import com.example.aplicacionv1_2.dao.DaoBares
import com.example.aplicacionv1_2.dialogues.DialogDeleteBar
import com.example.aplicacionv1_2.dialogues.DialogNewBar
import com.example.aplicacionv1_2.dialogues.DialogUpdateBar
import com.example.aplicacionv1_2.models.Bar

class Controller (val context : Context) {
    lateinit var listBares : MutableList<Bar>
    lateinit var adapterBar: AdapterBar
    private lateinit var addButton: Button

    private lateinit var layoutManager: LinearLayoutManager
    init {
        initData()
    }

    private fun initData() {
        listBares = DaoBares.myDao.getDataBares().toMutableList()
        setAdapter()
        initOnClickListener()
    }

    private fun initOnClickListener() {
        /*binding.btn_Add.setOnClickListener{
            addBar()
        }*/
        addButton = (context as Activity).findViewById(R.id.btn_add)
        addButton.setOnClickListener {
            addBar()
        }
    }

    private fun addBar() {
        Toast.makeText(context,"Añadiremos un nuevo hotel", Toast.LENGTH_LONG).show()
        val dialog = DialogNewBar(){
            bar -> okOnNewBar(bar)
        }
        val myActivity = context as MainActivity
    }

    private fun okOnNewBar(bar: Bar) {
        listBares.add(listBares.size,bar)
        adapterBar.notifyItemInserted(listBares.lastIndex)
        layoutManager.scrollToPositionWithOffset(listBares.lastIndex,20)
    }

    private fun setAdapter() {
        val myActivity = context as MainActivity
        adapterBar = AdapterBar(
            listBares,
            { pos -> delBar(pos) },
            { pos -> updateBar(pos) }
        )
        myActivity.binding.myRecyclerView.adapter = adapterBar

        // Aquí inicializas y asignas el layoutManager al RecyclerView
        layoutManager = LinearLayoutManager(context)
        myActivity.binding.myRecyclerView.layoutManager = layoutManager
    }

    private fun updateBar(pos: Int) {
        val editDialog = DialogUpdateBar(listBares.get(pos)){
            updateBar -> okOnUpdateBar(updateBar,pos)
        }
    }

    private fun okOnUpdateBar(updateBar: Bar, pos: Int) {
        listBares.removeAt(pos)
        adapterBar.notifyItemRemoved(pos)
        listBares.add(pos,updateBar)
        adapterBar.notifyItemInserted(pos)
        layoutManager.scrollToPositionWithOffset(pos,20)
    }

    private fun delBar(pos: Int) {
        val  myActivity = context as MainActivity

        val dialog = DialogDeleteBar(
            pos, listBares.get(pos).name
        ){
            position -> okOnDeleteBar(position)
        }

        myActivity.binding.myRecyclerView.adapter?.notifyItemRemoved(pos)
    }

    private fun okOnDeleteBar(position: Int) {
        listBares.removeAt(position)
        adapterBar.notifyItemRemoved(position)
        Toast.makeText(context,"Bar Borrado", Toast.LENGTH_LONG).show()
    }

}