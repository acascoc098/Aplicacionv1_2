package com.example.aplicacionv1_2.dao

import com.example.aplicacionv1_2.interfaces.InterfaceDao
import com.example.aplicacionv1_2.models.Bar
import com.example.aplicacionv1_2.object_models.Repository

class DaoBares private constructor(): InterfaceDao {
    companion object{
        val myDao: DaoBares by lazy {
            DaoBares()
        }
    }

    override fun getDataBares(): List<Bar> = Repository.listBares
}