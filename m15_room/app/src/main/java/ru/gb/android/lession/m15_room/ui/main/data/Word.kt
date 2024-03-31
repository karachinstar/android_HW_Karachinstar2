package ru.gb.android.lession.m15_room.ui.main.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "AllWords")
data class Word(
    @PrimaryKey
    @ColumnInfo(name = "Word")
    val word: String,
    @ColumnInfo(name = "Count")
    val count: Int
)