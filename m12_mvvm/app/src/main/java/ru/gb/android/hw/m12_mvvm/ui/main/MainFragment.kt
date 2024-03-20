package ru.gb.android.hw.m12_mvvm.ui.main

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import ru.gb.android.hw.m12_mvvm.databinding.FragmentMainBinding

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

        binding.buttonSearch.setOnClickListener {
            viewModel.onButtonSearchClick(binding.searchEditText.text.toString())
        }

        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.state
                    .collect { state ->
                        when (state) {
                            State.Loading -> {
                                binding.progress.isVisible = true
                                binding.buttonSearch.isEnabled = false
                            }

                            State.Success -> {
                                binding.progress.isVisible = false
                                binding.searchEditText.addTextChangedListener(object : TextWatcher {
                                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                                    }

                                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                                    }

                                    // После изменения текста
                                    override fun afterTextChanged(s: Editable) {
                                        binding.buttonSearch.isEnabled = s.length >= 3
                                    }
                                })
                            }

                            is State.Result -> {
                                binding.buttonSearch.isEnabled = true
                                binding.searchResults.text = state.value
                            }
                        }
                    }
            }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}