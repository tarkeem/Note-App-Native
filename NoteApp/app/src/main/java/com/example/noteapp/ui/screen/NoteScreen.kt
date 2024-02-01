package com.example.noteapp.ui.screen

import android.provider.CalendarContract.Colors
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.data.database.model.MyNote
import com.example.noteapp.router.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun NoteScreen(navController: NavController,vm: NoteViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent(vm) }) {
        Scaffold(
            topBar = { NoteAppBar(drawerState = drawerState,scope)},
            floatingActionButton = { NoteFloatingActionBt(navController = navController)} )
        {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(it)) {
                NoteContent(vm)
            }
        }
    }
    
}

@Composable
fun NoteContent(vm: NoteViewModel) {


    val notes:List<MyNote> by vm.noteList.observeAsState(initial = emptyList())
   

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top, modifier = Modifier
            .fillMaxSize()
            .padding(8.dp), content = {
            items(notes){
                NoteItem(note = it)
            }
        })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteAppBar(drawerState: DrawerState,scope: CoroutineScope) {
    TopAppBar(
        title = {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription ="drawer" )

                }
                Text(text = "Note Screen")
            }

        },
    )
}
@Composable
fun NoteFloatingActionBt(navController: NavController)
{
    FloatingActionButton(onClick = { navController.navigate(Screen.EditScreen.route) }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "add")

    }
}

@Composable
fun DrawerContent(vm:NoteViewModel)
{
    ModalDrawerSheet {
        Column {

            Image(painter = painterResource(id =com.example.noteapp.R.drawable.book), contentDescription = "book image")
            Row(verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween,) {
                Text(text = "Dark Theme")
                Switch(checked =vm.isDarkTheme.value , onCheckedChange ={
                    vm.isDarkTheme.value=it
                } )
            }

        }
    }

}

@Composable
fun NoteItem(note: MyNote)
{
    Column {
        Card(shape = RoundedCornerShape(10)) {
            Row(modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()) {
                Box(modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .border(
                        BorderStroke(
                            2.dp,
                            SolidColor(Color.Black)
                        ),
                        CircleShape
                    )
                    .align(alignment = Alignment.CenterVertically)
                )
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = note.title, fontSize = 30.sp)
                    Text(text = note.content)
                }
            }

        }
        Spacer(modifier = Modifier.height(7.dp))
    }

}