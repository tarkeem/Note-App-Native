package com.example.noteapp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyNote(
    @PrimaryKey(autoGenerate = true)val id:Int=0,
    @ColumnInfo(name = "title")val title:String,
    @ColumnInfo(name = "content") val content: String,

)
