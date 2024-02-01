package com.example.noteapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteapp.data.database.model.MyNote

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(note: MyNote)
    @Query("SELECT * FROM MyNote")
    fun getNotes():List<MyNote>
    @Delete
    fun deleteNote(note: MyNote)

}