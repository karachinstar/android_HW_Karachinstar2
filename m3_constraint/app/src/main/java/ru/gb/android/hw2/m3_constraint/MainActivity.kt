package ru.gb.android.hw2.m3_constraint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gb.android.hw2.m3_constraint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}