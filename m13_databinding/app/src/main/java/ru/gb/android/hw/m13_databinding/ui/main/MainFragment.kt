package ru.gb.android.hw.m13_databinding.ui.main

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.coroutines.delay
import ru.gb.android.hw.m13_databinding.R

import ru.gb.android.hw.m13_databinding.databinding.FragmentMainBinding
import kotlin.concurrent.thread

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.editText.observe(viewLifecycleOwner, Observer {
            if (binding.search.text.length >= 3){
                viewModel.onButtonSearchClick(it.toString())
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}