package ru.gb.android.lession.m16_architecture.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.gb.android.lession.m16_architecture.data.UsefulActivityDto
import ru.gb.android.lession.m16_architecture.domain.GetUsefulActivityUseCase
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsefulActivityUseCase: GetUsefulActivityUseCase
): ViewModel() {
    private val defaultActivity = UsefulActivityDto(
        "x",
        "x",
        0,
        0.1F,
        "x",
        "x",
        0.1F
    )
    private val _activityFlow = MutableStateFlow(defaultActivity)
    val activityFlow = _activityFlow.asStateFlow()

    suspend fun reloadUsefulActivity() {
        _activityFlow.value = getUsefulActivityUseCase.execute()
    }
}