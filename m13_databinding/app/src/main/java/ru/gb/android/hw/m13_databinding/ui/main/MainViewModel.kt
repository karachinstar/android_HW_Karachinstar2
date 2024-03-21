package ru.gb.android.hw.m13_databinding.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    var editText = MutableLiveData<String>()


//    private val _isSearching = MutableLiveData(false)
//    val isSearching: LiveData<Boolean> = _isSearching

//
//    var isSearchInProgress = false
//        private set

//    fun onButtonSearchClick(query: String) {
//        isSearchInProgress = true
//        _isSearching.value = true
//
//        viewModelScope.launch {
//            delay(5000)
//            isSearchInProgress = false
//            _isSearching.value = false
//            _searchResult.value = "По запросу $query ничего не найдено"
//        }
//    }

    fun onButtonSearchClick(query: String) {
        viewModelScope.launch {
            _state.value = State.Loading
            delay(5000)
            _state.value = State.Result("По запросу $query ничего не найдено")
        }
    }

}