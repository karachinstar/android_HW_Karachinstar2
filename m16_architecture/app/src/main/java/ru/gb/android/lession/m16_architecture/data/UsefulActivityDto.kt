package ru.gb.android.lession.m16_architecture.data

import ru.gb.android.lession.m16_architecture.entity.UsefulActivity

class UsefulActivityDto(
    override val activity: String,
    override val type: String,
    override val participants: Int,
    override val price: Float,
    override val link: String?,
    override val key: String,
    override val accessibility: Float
) : UsefulActivity