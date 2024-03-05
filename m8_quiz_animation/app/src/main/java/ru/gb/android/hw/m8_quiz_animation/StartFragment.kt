package ru.gb.android.hw.m8_quiz_animation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import ru.gb.android.hw.m8_quiz_animation.databinding.FragmentStartBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.startButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_startFragment_to_quizFragment)
            //findNavController().navigate(R.id.action_startFragment_to_quizFragment)
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}