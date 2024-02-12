package ru.gb.android.hw2.m2_layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.gb.android.hw2.m2_layout.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
//    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customView.setMessageTopText(resources.getString(R.string.top_text))
        binding.customView.setMessageBottomText(resources.getString(R.string.bottom_text))
    }
}