package ru.gb.android.hw.m13_databinding.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private var searchJob: Job? = null

    var editText = MutableStateFlow("")

    private suspend fun searchString() {
        _state.value = State.Loading
        delay(5000)
        _state.value = State.Result("По запросу ${editText.value} ничего не найдено")

    }


    fun onTextChanged(str: String) {
        searchJob?.cancel()
        if (str.length < 3)
            _state.value = State.Success
        else {
            editText.value = str
            searchJob = editText
                .debounce(1000)
                .onEach {
                    searchString()
                }
                .launchIn(viewModelScope)
        }
    }
//    fun onButtonSearchClick(query: String) {
//        viewModelScope.launch {
//            _state.value = State.Loading
//            delay(5000)
//            _state.value = State.Result("По запросу $query ничего не найдено")
//        }
//    }
}