package ru.gb.android.lession.m16_architecture.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

import ru.gb.android.lession.m16_architecture.R
import ru.gb.android.lession.m16_architecture.databinding.FragmentMainBinding


@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding by lazy { _binding!! }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.activityFlow.collect {
                binding.dataUsefulActivity.text = it.activity
            }
        }

        if (savedInstanceState == null) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.reloadUsefulActivity()
            }
        }

        binding.buttonRefresh.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.reloadUsefulActivity()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}