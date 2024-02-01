package com.example.noteapp.data.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.noteapp.data.database.AppDatabase
import com.example.noteapp.data.database.dao.NoteDao
import com.example.noteapp.data.database.model.MyNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class repoImpl(cxt:Context):repo {
    private lateinit var noteDao: NoteDao
    val allNotes = MutableLiveData<List<MyNote>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    init {
        val db =
            Room.databaseBuilder(
                cxt,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            ).allowMainThreadQueries().build()
        noteDao=db.noteDao()
    }

    override  fun getAllNotes() {
        coroutineScope.launch(Dispatchers.IO) {
       allNotes.postValue( noteDao.getNotes())
        }

    }

    override suspend fun InsetNote(note: MyNote) {
        noteDao.insertNotes(note)
    }
}