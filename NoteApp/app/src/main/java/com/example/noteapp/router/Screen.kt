package com.example.noteapp.router

sealed class Screen(val route:String) {

    object NoteScreen:Screen("noteScreen")
    object EditScreen:Screen("editScreen")
}