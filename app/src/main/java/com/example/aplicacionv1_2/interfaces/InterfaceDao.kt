package com.example.aplicacionv1_2.interfaces

import com.example.aplicacionv1_2.models.Bar

interface InterfaceDao {
    fun getDataBares() : List<Bar>
}