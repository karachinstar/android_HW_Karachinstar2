package ru.gb.android.lession.m16_architecture.entity

interface UsefulActivity{
    val activity :String
    val type: String
    val participants: Int
    val price : Float
    val link : String?
    val key : String
    val accessibility : Float
}