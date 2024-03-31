package ru.gb.android.lession.m15_room

import android.app.Application
import androidx.room.Room
import ru.gb.android.lession.m15_room.ui.main.data.WordDatabase


class App: Application() {
    lateinit var db: WordDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            WordDatabase::class.java,
            "db"
        ).build()
    }
}