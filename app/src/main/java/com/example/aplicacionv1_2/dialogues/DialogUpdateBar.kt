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
import com.example.aplicacionv1_2.models.ArgumentosBar
import com.example.aplicacionv1_2.models.Bar

class DialogUpdateBar(
    val barToUpdate : Bar,
    val updateDialogBar: (Bar) -> Unit
): DialogFragment() {
    lateinit var activity: MainActivity
    val ARGUMENT_NAME: String = ArgumentosBar.ARGUMENT_NAME
    val ARGUMENT_CITY: String = ArgumentosBar.ARGUMENT_CITY
    val ARGUMENT_PROVINCE: String = ArgumentosBar.ARGUMENT_PROVINCE
    val ARGUMENT_PHONE: String = ArgumentosBar.ARGUMENT_PHONE
    val ARGUMENT_IMAGE: String = ArgumentosBar.ARGUMENT_IMAGE

    init {
        val args = Bundle().apply {
            putString(ARGUMENT_NAME, barToUpdate.name)
            putString(ARGUMENT_CITY, barToUpdate.city)
            putString(ARGUMENT_PROVINCE, barToUpdate.province)
            putString(ARGUMENT_PHONE, barToUpdate.phone)
            putString(ARGUMENT_IMAGE, barToUpdate.image)
        }
        this.arguments = args
    }

    private fun setValuesIntoDialog(viewDialogUpdateBar: View, arguments: Bundle?){
        val binding = DialogAddBarBinding.bind(viewDialogUpdateBar)
        if (arguments != null) {
            binding.txtViewName.setText(arguments?.getString( ARGUMENT_NAME))
            binding.txtViewCity.setText(arguments?.getString( ARGUMENT_CITY))
            binding.txtViewProvence.setText(arguments?.getString( ARGUMENT_PROVINCE))
            binding.txtViewPhone.setText(arguments?.getString( ARGUMENT_PHONE))
            binding. xtViewUrlImage.setText(arguments?.getString( ARGUMENT_IMAGE))
        }
    }

    private fun recoverDataLayout(view: View): Any{
        val binding = DialogAddBarBinding.bind(view)
        return Bar(
            binding.txtViewName.text.toString(),
            binding.txtViewCity.text.toString(),
            binding.txtViewProvence.text.toString(),
            binding.txtViewPhone.text.toString(),
            binding.txtViewUrlImage.text.toString()
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        val inflater = requireActivity().layoutInflater;
        val viewDialogUpdateBar = inflater.inflate(R.layout.dialog_add_bar, null)
        setValuesIntoDialog(viewDialogUpdateBar, this.arguments)

        builder.setView(viewDialogUpdateBar)
            .setPossitiveButton("Aceptar",
                DialogInterface.OnClickListener{dialog, which ->
                val updateBar = recoverDataLayout(viewDialogUpdateBar) as Bar
                if (updateBar.name.isNullOrEmpty() ||
                    updateBar.city.isNullOrEmpty() ||
                    updateBar.province.isNullOrEmpty() ||
                    updateBar.phone.isNullOrEmpty()
                ){
                    Toast.makeText(activity, "Algún campo está vacío", Toast.LENGTH_LONG).show()
                    getDialog()?.cancel()
                }else{
                    updateDialogBar(updateBar)
                }
            })
            .setNegativeButton("Cancelar",
                DialogInterface.OnClickListener{dialog, which ->
                    getDialog()?.cancel()
                })
        return builder.create()
    }
}