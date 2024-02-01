package com.example.noteapp.ViewModel

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.database.model.MyNote
import com.example.noteapp.data.repo.repoImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(cxt:Context):ViewModel() {
    val rep= repoImpl(cxt)

    val isDarkTheme= mutableStateOf<Boolean>(false)
    private val nn: MutableLiveData<List<MyNote>> =rep.allNotes
    val  noteList: LiveData<List<MyNote>>
        get() = nn



     private val noteState: MutableLiveData<noteStateData> by lazy {
        MutableLiveData()
    }
    val  noteLiveState:LiveData<noteStateData> get() = noteState
    fun getAllNotes()
    {
        rep.getAllNotes()

    }
    fun insertNote(title:String,content:String){
        viewModelScope.launch {
            rep.InsetNote(
                MyNote(
                    content = content,
                    title = title
                )
            )
        }
    }
    fun emitNoteState(
        isLoading: Boolean=false,
        notes: List<MyNote>
    )
    {
        val data=noteStateData(isLoading,notes)
        noteState.postValue(data)
    }
    data class noteStateData(
        val isLoading:Boolean,
        val notes: List<MyNote>
    )

}