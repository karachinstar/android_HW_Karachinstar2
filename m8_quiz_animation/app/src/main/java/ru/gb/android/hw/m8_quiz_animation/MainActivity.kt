package ru.gb.android.hw.m8_quiz_animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gb.android.hw.m8_quiz_animation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}