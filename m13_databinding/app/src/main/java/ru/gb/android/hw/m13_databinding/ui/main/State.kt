package ru.gb.android.hw.m13_databinding.ui.main

import android.content.res.Resources
import ru.gb.android.hw.m13_databinding.R

sealed class State(
    val isLoading: Boolean = false,
    open val text: String? = "Здесь будет отображаться результат"
){
    data object Loading : State(isLoading = true)
    data object Success : State()
    data class Result(
        override val text: String?
    ) : State(
        text = text
    )
}