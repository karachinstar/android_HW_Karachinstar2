package ru.gb.android.hw1.m1_hello_world

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.gb.android.hw1.m1_hello_world.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var countPass = 0
    var countPlace = 49
    private var binding: ActivityMainBinding? = null

    //    val binding = ActivityMainBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        starSetting()
        binding!!.buttonPlus.setOnClickListener {
            if (countPass < 49) {
                binding!!.buttonMinus.isEnabled = true
                countPass++
                binding!!.centralText.text = "Осталось мест: ${countPlace - countPass}"
                binding!!.centralText.setTextColor(Color.BLUE)
                binding!!.textCountPassengers.text = countPass.toString()

            } else {
                countPass++
                binding!!.buttonRemove.visibility = View.VISIBLE
                binding!!.centralText.text = "Пассажиров слишком много!"
                binding!!.centralText.setTextColor(Color.RED)
                binding!!.textCountPassengers.text = countPass.toString()
            }
        }
        binding!!.buttonMinus.setOnClickListener {
            if (countPass > 1 && countPass <= 50) {
                countPass--
                binding!!.buttonRemove.visibility = View.INVISIBLE
                binding!!.centralText.text = "Осталось мест: ${countPlace - countPass}"
                binding!!.centralText.setTextColor(Color.BLUE)
                binding!!.textCountPassengers.text = countPass.toString()
            } else if (countPass > 50) {
                countPass--
                binding!!.textCountPassengers.text = countPass.toString()
            } else {
                starSetting()
            }
        }
        binding!!.buttonRemove.setOnClickListener {
            starSetting()
        }

    }

    private fun starSetting() {
        countPass = 0
        binding!!.buttonMinus.isEnabled = false
        binding!!.buttonRemove.visibility = View.INVISIBLE
        binding!!.textCountPassengers.text = countPass.toString()
        binding!!.centralText.text = "Все места свободны"
        binding!!.centralText.setTextColor(Color.GREEN)
    }
}
