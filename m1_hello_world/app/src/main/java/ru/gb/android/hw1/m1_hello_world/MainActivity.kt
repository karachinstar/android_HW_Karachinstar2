package ru.gb.android.hw1.m1_hello_world

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.gb.android.hw1.m1_hello_world.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var counter = 0
    private val binding = ActivityMainBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        starSetting()

    }

    private fun starSetting(){
        binding.buttonMinus.visibility = View.INVISIBLE
    }
}
