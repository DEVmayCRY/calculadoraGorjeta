package com.komat.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.komat.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

//Old way with findViewById()
//val myButton: Button = findViewById(R.id.my_button)
//myButton.text = "A button"
//
//Better way with view binding
//val myButton: Button = binding.myButton
//myButton.text = "A button"
//
//Best way with view binding and no extra variable
//binding.myButton.text = "A button"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calcButton.setOnClickListener { calculateTip() }
    }

    fun calculateTip() {
        val stringInTextField = binding.custoDoSercico.text.toString()
        val cost = stringInTextField.toDouble()
        val idSelecionado = binding.tipOpicoes.checkedRadioButtonId
        val porsentagemGorj = when (idSelecionado) {
            R.id.option_20 -> 0.20
            R.id.option_18 -> 0.18
            else -> 0.15
        }
        var tip = porsentagemGorj * cost
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

}