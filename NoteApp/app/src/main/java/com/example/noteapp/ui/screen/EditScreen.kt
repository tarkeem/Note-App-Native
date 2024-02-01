package com.example.noteapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteapp.ViewModel.NoteViewModel

@Composable
fun EditScreen(navController: NavController,vm: NoteViewModel) {
    Scaffold(topBar = { AppBar()} ) {
        Box(modifier = Modifier.padding(it)) {
            Content(vm,navController)
        }
    }
}

@Composable
fun Content(vm:NoteViewModel,navController: NavController) {
    val title=remember{ mutableStateOf<String>("") }
    val note=remember{ mutableStateOf<String>("") }
   Column(horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center) {
      ContentTextField(label = "Title", txt =title , onTextChange ={
          title.value=it
      } )
       Spacer(modifier = Modifier.height(10.dp))
       ContentTextField(label = "Note", txt =note, onTextChange ={
           note.value=it
       } )
       Spacer(modifier = Modifier.height(10.dp))
       IconButton(onClick = {
           vm.insertNote(title.value,note.value)
           vm.getAllNotes()
           navController.popBackStack()
       },) {
           Icon(imageVector = Icons.Filled.Check, contentDescription = "save")

       }
   }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
   TopAppBar(title = { Text(text = "Edit Screen")})
}



@Composable
private fun ContentTextField(
    modifier: Modifier = Modifier,
    label: String,
    txt: MutableState<String>,
    onTextChange: (String) -> Unit
) {

    TextField(
        value =txt.value ,
        onValueChange = {txt.value=it},
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),

    )
}