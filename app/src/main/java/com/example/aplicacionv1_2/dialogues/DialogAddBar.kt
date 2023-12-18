package com.example.aplicacionv1_2.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.aplicacionv1_2.MainActivity
import com.example.aplicacionv1_2.R
import com.example.aplicacionv1_2.models.Bar

class DialogNewBar(val onNewBarDialog: (Bar) -> Unit) : DialogFragment() {
    lateinit var activity: MainActivity

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(activity)

            val inflater = requireActivity().layoutInflater

            val viewDialogNewBar = inflater.inflate(R.layout.dialog_new_bar, null)
            builder.setView(viewDialogNewBar)
                // Add action buttons
                .setPositiveButton("Añadir",
                    DialogInterface.OnClickListener { dialog, id ->
                        val newBar = recoverDataLayout(viewDialogNewBar) as Bar
                        if (
                            newBar.name.isNullOrEmpty() ||
                            newBar.city.isNullOrEmpty() ||
                            newBar.province.isNullOrEmpty() ||
                            newBar.phone.isNullOrEmpty()
                        ) {
                            Toast.makeText(activity, "Algún campo está vacío", Toast.LENGTH_LONG).show()
                            dialog.cancel()
                        } else {
                            onNewBarDialog(newBar)
                        }
                    })
                .setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

            builder.create()
        }
    }

    private fun recoverDataLayout(view: View): Any {
        val binding = DialogNewBarBinding.bind(view)
        return Bar(
            binding.txtViewName.text.toString(),
            binding.txtViewCity.text.toString(),
            binding.txtViewProvence.text.toString(),
            binding.txtViewPhone.text.toString(),
            binding.txtViewUrlImage.text.toString()
        )
    }
}
