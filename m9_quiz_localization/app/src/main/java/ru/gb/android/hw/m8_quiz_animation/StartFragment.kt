package ru.gb.android.hw.m9_quiz_localization

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import ru.gb.android.hw.m9_quiz_localization.databinding.FragmentStartBinding
import java.text.SimpleDateFormat


class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd-MM-yy")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.startButton.animate().apply {
            duration = 3000
            rotation(360f)
            translationY(-200f)
           // RippleDrawable.INSET_UNDEFINED

        }.start()

        binding.birthdayMan.setOnClickListener {
            val constraints = CalendarConstraints.Builder()
                .setOpenAt(calendar.timeInMillis)
                .build()
            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setCalendarConstraints(constraints)
                .setTitleText(resources.getString(R.string.enterYourDateOfBirthTitle))
                .build()
            dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
                calendar.timeInMillis = timeInMillis
                Snackbar.make(binding.birthdayMan, "${getText((R.string.snackbarStart))} " + dateFormat.format(calendar.time), Snackbar.LENGTH_SHORT).show()
            }
            dateDialog.show(parentFragmentManager, "Asd")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startButton.setOnClickListener {
            //Navigation.findNavController(it).navigate(R.id.action_startFragment_to_quizFragment)
            findNavController().navigate(R.id.action_startFragment_to_quizFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}