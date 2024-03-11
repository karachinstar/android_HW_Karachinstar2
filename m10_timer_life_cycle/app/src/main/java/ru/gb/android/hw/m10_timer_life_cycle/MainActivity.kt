package ru.gb.android.hw.m10_timer_life_cycle

import android.os.Bundle
import android.widget.SeekBar

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.gb.android.hw.m10_timer_life_cycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var job: Job? = null
    private var timeLeft: Int = 0

    companion object {
        private const val TIME_LEFT_KEY = "timeLeft"
        private const val IS_RUNNING_KEY = "isRunning"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val realProgress = progress
                binding.realProgressView.text = realProgress.toString()
                binding.progressBar.progress = realProgress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        binding.buttonStartStop.setOnClickListener {
            if (job?.isActive == true) {
                job?.cancel()
                binding.buttonStartStop.text = "START"
                binding.seekBar.isEnabled = true
            } else {
                timeLeft = binding.seekBar.progress
                binding.progressBar.progress = timeLeft
                binding.buttonStartStop.text = "STOP"
                binding.seekBar.isEnabled = false

                job = CoroutineScope(Dispatchers.Main).launch {
                    for (i in timeLeft downTo 0) {
                        delay(1000)
                        timeLeft = i
                        binding.realProgressView.text = i.toString()
                        binding.progressBar.progress = i
                    }
                    binding.buttonStartStop.text = "START"
                    binding.seekBar.isEnabled = true
                }
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TIME_LEFT_KEY, timeLeft)
        outState.putBoolean(IS_RUNNING_KEY, job?.isActive == true)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        timeLeft = savedInstanceState.getInt(TIME_LEFT_KEY)
        val isRunning = savedInstanceState.getBoolean(IS_RUNNING_KEY)

        binding.realProgressView.text = timeLeft.toString()
        binding.progressBar.progress = timeLeft
        binding.seekBar.progress = timeLeft

        if (isRunning) {
            binding.buttonStartStop.performClick()
        }
    }
}