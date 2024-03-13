package ru.gb.android.hw.m11_timer_data_storage

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.gb.android.hw.m11_timer_data_storage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() {
            return _binding!!
        }
    private lateinit var repository: Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = Repository(this)

        binding.textViewSaveData.text=repository.getText()

        binding.saveDataButton.setOnClickListener{
            val outString = binding.editText.text.toString()
            repository.saveText(outString)
            binding.textViewSaveData.text=outString
        }

        binding.clearDataButton.setOnClickListener {
            repository.clearText()
            binding.textViewSaveData.text=""
        }
    }
    override fun onPause() {
        super.onPause()
        // Сохранение текста при приостановке активности
        val currentText = binding.editText.text.toString()
        repository.saveText(currentText)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}