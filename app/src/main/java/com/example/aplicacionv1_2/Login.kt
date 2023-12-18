package com.example.aplicacionv1_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    private val MYUSER = "acascoc098"
    private val MYPASS = "acascoc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        usernameEditText = findViewById(R.id.usuario)
        passwordEditText = findViewById(R.id.contraseña)
        loginButton = findViewById(R.id.boton)

        loginButton.setOnClickListener {
            iniciarLogin()
        }
    }

    private fun iniciarLogin() {
        val usernameInput = usernameEditText.text.toString()
        val passwordInput = passwordEditText.text.toString()

        if (estaLogeado(usernameInput, passwordInput)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun estaLogeado(username: String, password: String): Boolean {
        return username == MYUSER && password == MYPASS
    }
}
