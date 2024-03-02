package ru.gb.android.less.m7_quiz_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.gb.android.less.m7_quiz_fragments.databinding.FragmentQuizBinding
import ru.gb.android.less.m7_quiz_fragments.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_startFragment)
        }
        return view
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}