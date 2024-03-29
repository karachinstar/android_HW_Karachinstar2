package ru.gb.android.lession14.m14_retrofit.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.gb.android.lession14.m14_retrofit.ui.main.retrofit.Person
import ru.gb.android.lession14.m14_retrofit.ui.main.retrofit.Repository

class MainViewModel : ViewModel() {
    // вью модель не должна напрямую общаться с ретрофитом, только через репозиторий
    private val repository = Repository()

    // это изменяемая флоу
    private val _getPerson = MutableStateFlow<Person?>(null)
    // это не изменяемая "версия" флоу _getPerson, на которую подписывается фрагмент, когда в нее попадают новые данные фрагмент реагирует на них
    val getPerson = _getPerson.asStateFlow()

    suspend fun update() {
        //при вызове этой функции записываем во флоу данные которые придут из репозитория -> ретрофита
        _getPerson.value = repository.getData()
    }

}