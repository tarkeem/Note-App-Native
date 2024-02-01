package com.example.noteapp.data.repo

import androidx.lifecycle.LiveData
import com.example.noteapp.data.database.model.MyNote


interface repo {

     fun getAllNotes()
    suspend fun InsetNote(note: MyNote)

}