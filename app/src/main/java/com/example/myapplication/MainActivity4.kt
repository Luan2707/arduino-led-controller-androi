package com.example.myapplication

import android.content.Intent // Asegúrate de importar Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class MainActivity4 : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Activa el soporte Edge-to-Edge si es necesario
        setContentView(R.layout.activity_main4)

        firebaseAuth = FirebaseAuth.getInstance() // Inicializa FirebaseAuth

        val btningresar: Button = findViewById(R.id.btningresar)
        val NewEmail: EditText = findViewById(R.id.NewEmail)
        val NewPass: EditText = findViewById(R.id.NewPass)

        btningresar.setOnClickListener {
            val email = NewEmail.text.toString().trim()
            val password = NewPass.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signIn(email, password) // Llama al método de autenticación
            } else {
                Toast.makeText(this, "Por favor ingrese email y contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun signIn(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    Toast.makeText(baseContext, "Bienvenido, UID: ${user?.uid}", Toast.LENGTH_SHORT).show()

                    // Inicia MainActivity después de iniciar sesión exitosamente
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Cierra MainActivity4 para que no se pueda volver con el botón atrás
                } else {
                    Toast.makeText(baseContext, "Error de Email o Contraseña", Toast.LENGTH_SHORT).show()
                }
            }
    }
}

