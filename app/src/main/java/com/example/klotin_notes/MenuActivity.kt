package com.example.klotin_notes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.klotin_notes.firstapp.FirstAppActivity
import com.example.klotin_notes.imccalculator.ImcCalculatorActivity
import com.example.klotin_notes.simpleCalculator.calculator2App
import com.example.klotin_notes.simpleCalculator.calculatorApp
import com.example.klotin_notes.simpleCalculator.converterApp
import com.example.klotin_notes.simpleCalculator.spinnerCalculator

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnGreet = findViewById<Button>(R.id.btnGreet)
        val btnIMC = findViewById<Button>(R.id.btnIMC)
        val btnCalculator = findViewById<Button>(R.id.btnCalculator)
        val btnCalculator2 = findViewById<Button>(R.id.btnCalculator2)
        val btnCalculatorS = findViewById<Button>(R.id.btnCalculatorS)
        val btnComberter = findViewById<Button>(R.id.btnComberter)
        btnGreet.setOnClickListener { navigateToGreet() }
        btnIMC.setOnClickListener { navigateToIMC() }
        btnCalculator.setOnClickListener { navigateToCalculator() }
        btnCalculator2.setOnClickListener { navigateToCalculator2() }
        btnCalculatorS.setOnClickListener { navigateToCalculatorS() }
        btnComberter.setOnClickListener { navigateToComberter() }

    }
    private fun navigateToGreet() {
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToIMC() {
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToCalculator() {
        val intent = Intent(this,calculatorApp::class.java)
        startActivity(intent)
    }
    private fun navigateToCalculator2() {
        val intent = Intent(this,calculator2App::class.java)
        startActivity(intent)
    }
    private fun navigateToCalculatorS() {
        val intent = Intent(this,spinnerCalculator::class.java)
        startActivity(intent)
    }
    private fun navigateToComberter() {
        val intent = Intent(this,converterApp::class.java)
        startActivity(intent)
    }
}