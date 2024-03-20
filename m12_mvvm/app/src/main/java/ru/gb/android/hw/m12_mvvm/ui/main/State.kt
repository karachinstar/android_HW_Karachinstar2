package ru.gb.android.hw.m12_mvvm.ui.main

sealed class State{
    object Loading : State()
    object Success : State()
    data class Result(val value: String? = null) : State()
}