package ru.gb.android.less.m5_quiz_resources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gb.android.less.m5_quiz_resources.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}