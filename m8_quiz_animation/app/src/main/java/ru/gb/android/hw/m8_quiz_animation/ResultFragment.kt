package ru.gb.android.hw.m8_quiz_animation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import ru.gb.android.hw.m8_quiz_animation.databinding.FragmentResultBinding


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
            Navigation.findNavController(it).navigate(R.id.action_resultFragment_to_quizFragment)
            //findNavController().navigate(R.id.action_resultFragment_to_quizFragment)
        }
        return view
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}