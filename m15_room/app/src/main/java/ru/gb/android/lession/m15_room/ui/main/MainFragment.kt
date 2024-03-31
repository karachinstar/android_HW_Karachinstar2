package ru.gb.android.lession.m15_room.ui.main

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.gb.android.lession.m15_room.App

import ru.gb.android.lession.m15_room.R
import ru.gb.android.lession.m15_room.databinding.FragmentMainBinding
import ru.gb.android.lession.m15_room.ui.main.data.State

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val wordDao = (requireContext().applicationContext as App).db.wordDao()
                return MainViewModel(wordDao) as T
            }
        }
    }
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeOnState()
        subscribeOnChangeData()

        binding.buttonSave.setOnClickListener {
            viewModel.onSave(binding.enteredText.text.toString())
        }

        binding.buttonClear.setOnClickListener {
            viewModel.onDelete()
        }

        binding.enteredText.doOnTextChanged { text, _, _, _ ->
            viewModel.changeState(text)
        }
    }

    private fun subscribeOnChangeData() {
        lifecycleScope.launch {
            viewModel.getAllWithLimit.collect { list ->
                binding.dataFromBase.text = list.joinToString("\n")
            }
        }
    }

    private fun subscribeOnState() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    State.ERROR -> {
                        binding.buttonSave.isEnabled = false
                        binding.outlinedTextField.isErrorEnabled = true
                        binding.outlinedTextField.error = resources.getString(R.string.error_text)
                    }

                    State.SUCCESS -> {
                        binding.buttonSave.isEnabled = true
                        binding.outlinedTextField.isErrorEnabled = false
                    }

                    State.START -> binding.buttonSave.isEnabled = false
                }
            }
        }
    }

}