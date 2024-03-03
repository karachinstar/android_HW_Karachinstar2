package ru.gb.android.less.m7_quiz_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.gb.android.less.m7_quiz_fragments.databinding.FragmentQuizBinding
import ru.gb.android.less.m7_quiz_fragments.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root
        val results = args.result
        binding.result.setText(results)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_quizFragment)
        }
        return view
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}