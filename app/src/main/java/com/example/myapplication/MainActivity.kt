package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Activa el soporte Edge-to-Edge
        setContentView(R.layout.activity_main)

        // Configurar los insets para el diseño
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar el botón para navegar a MainActivity2
        val buttonToSecondActivity = findViewById<Button>(R.id.button_to_second_activity)
        buttonToSecondActivity.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java) // Inicia MainActivity2
            startActivity(intent)
        }
    }
}

