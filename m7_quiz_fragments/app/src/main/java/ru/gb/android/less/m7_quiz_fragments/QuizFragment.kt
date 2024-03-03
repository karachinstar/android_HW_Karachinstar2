package ru.gb.android.less.m7_quiz_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.gb.android.less.m7_quiz_fragments.databinding.FragmentQuizBinding
import ru.gb.android.less.m7_quiz_fragments.databinding.FragmentStartBinding

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
        binding.button.setOnClickListener {
            val action = QuizFragmentDirections.actionQuizFragmentToResultFragment(getAnswersByUser().toString())
            findNavController().navigate(action)
        }
        return view
    }

    private fun getAnswersByUser(): Int {
        var correctAnswersCount = 0

        if (binding.question1.checkedRadioButtonId == binding.answer12.id) correctAnswersCount++
        if (binding.question2.checkedRadioButtonId == binding.answer21.id) correctAnswersCount++
        if (binding.question3.checkedRadioButtonId == binding.answer31.id) correctAnswersCount++

        return correctAnswersCount
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}