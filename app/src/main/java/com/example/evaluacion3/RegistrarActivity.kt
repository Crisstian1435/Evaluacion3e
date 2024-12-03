package com.example.evaluacion3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.evaluacion3.databinding.ActivityRegistrarBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegistrarActivity : AppCompatActivity() {


    private lateinit var binding : ActivityRegistrarBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = Firebase.auth


        binding.btnRegistrar.setOnClickListener{

            val correo: String = binding.etEmail.text.toString()
            val pass1: String = binding.etPass.text.toString()
            val pass2: String = binding.etPass2.text.toString()

            if (correo.isEmpty()){
                binding.etEmail.error = "Ingrese su correo"
                return@setOnClickListener
            }
            if (pass1.isEmpty()){
                binding.etPass.error = "Ingrese su Clave"
            }
            if (pass2.isEmpty()){
                binding.etPass2.error = "Confirme su Clave"
            }

            if(pass1 == pass2){
                registrarUsuario(correo, pass1)
            }

        }

        binding.btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun registrarUsuario(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(this, "El usuario se ha registrado correctamente", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
    }
}