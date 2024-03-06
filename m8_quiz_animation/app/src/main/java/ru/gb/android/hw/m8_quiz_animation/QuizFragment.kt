package ru.gb.android.hw.m8_quiz_animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import ru.gb.android.hw.m8_quiz_animation.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.question1.animate().apply {
            duration = 1000
            alpha(1f)

        }.start()

        binding.question2.animate().apply {
            duration = 2000
            alpha(1f)

        }.start()

        binding.question3.animate().apply {
            duration = 3000
            alpha(1f)

        }.start()

        binding.question4.animate().apply {
            duration = 4000
            alpha(1f)

        }.start()

        binding.question5.animate().apply {
            duration = 5000
            alpha(1f)

        }.start()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            val action = QuizFragmentDirections.actionQuizFragmentToResultFragment(getAnswersByUser().toString())
            findNavController().navigate(action)
        }
    }

    private fun getAnswersByUser(): Int {
        var correctAnswersCount = 0

        if (binding.question1.checkedRadioButtonId == binding.answer11.id) correctAnswersCount++
        if (binding.question2.checkedRadioButtonId == binding.answer22.id) correctAnswersCount++
        if (binding.question3.checkedRadioButtonId == binding.answer32.id) correctAnswersCount++
        if (binding.question4.checkedRadioButtonId == binding.answer41.id) correctAnswersCount++
        if (binding.question5.checkedRadioButtonId == binding.answer52.id) correctAnswersCount++

        return correctAnswersCount
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}