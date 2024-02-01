package com.example.noteapp

import NoteViewModelFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.data.repo.repoImpl
import com.example.noteapp.router.Screen
import com.example.noteapp.ui.screen.EditScreen
import com.example.noteapp.ui.screen.NoteScreen
import com.example.noteapp.ui.theme.NoteAppTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: NoteViewModel by viewModels(factoryProducer = {
        NoteViewModelFactory(
            this,
            this
        )
    })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

viewModel.getAllNotes()

        setContent {
            NoteAppTheme(darkTheme = viewModel.isDarkTheme.value) {
                MainScreen(viewModel)
            }

        }
    }
}

@Composable
fun MainScreen(viewModel: NoteViewModel) {
    var navController= rememberNavController()
    NavHost(navController = navController, startDestination =Screen.NoteScreen.route ){
        composable(Screen.NoteScreen.route){
            NoteScreen(navController,viewModel)
        }
        composable(Screen.EditScreen.route){
            EditScreen(navController,viewModel)
        }

    }}

