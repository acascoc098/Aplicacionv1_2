package com.example.aplicacionv1_2.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogDeleteBar(
    val pos : Int,
    val name: String,
    val onDeleteBarDialog: (Int) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("¿Desea borrar el bar $name?").setPositiveButton(
            "Si",DialogInterface.OnClickListener{dialog, which -> onDeleteBarDialog(pos)
            }).setNegativeButton(
                "No", DialogInterface.OnClickListener{dialog, which -> dialog.dismiss()})
        return builder.create()
    }
}