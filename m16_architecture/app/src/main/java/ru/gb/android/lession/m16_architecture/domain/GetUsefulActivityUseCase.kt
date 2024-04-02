package ru.gb.android.lession.m16_architecture.domain

import ru.gb.android.lession.m16_architecture.data.UsefulActivitiesRepository
import ru.gb.android.lession.m16_architecture.data.UsefulActivityDto
import javax.inject.Inject

class GetUsefulActivityUseCase @Inject constructor(
    private val usefulActivitiesRepository: UsefulActivitiesRepository
) {
    suspend fun execute(): UsefulActivityDto {
        return usefulActivitiesRepository.getUsefulActivity()
    }
}