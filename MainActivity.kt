package com.example.demoproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.demoproject.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }
        binding.costOfServiceEditText.setOnKeyListener{view , keycode, _ -> handleKeyEvent(view , keycode)}
    }
    private fun calculateTip()
    {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDouble()
        val selectedId = binding.tipOption.checkedRadioButtonId

        val tipPercentage = when(selectedId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount , formattedTip)
    }
    private fun handleKeyEvent(view: View, Keycode:Int):Boolean {
        if(Keycode ==  KeyEvent.KEYCODE_ENTER){
            //HIDE THE KEYBOARD
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken , 0)
            return true
        }
        return false
    }
}