package ru.gb.android.less.m7_quiz_fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gb.android.less.m7_quiz_fragments.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}