package com.example.noteapp.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.data.database.dao.NoteDao
import com.example.noteapp.data.database.model.MyNote


@Database(entities = [MyNote::class], version = 1)
abstract class AppDatabase:RoomDatabase()
{
    companion object {
        const val DATABASE_NAME = "note-database"
    }

    abstract fun noteDao():NoteDao
}