package com.example.projeto06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projeto06.ui.theme.Projeto06Theme
import com.example.projeto06.views.CharacterVMFactory
import com.example.projeto06.views.CharactersDetailsListScreen
import com.example.projeto06.views.CharactersListScreen
import com.example.projeto06.views.CharactersViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<CharactersViewModel>(){
            CharacterVMFactory(
                (this.applicationContext as FinalSpaceApplication).repositoy
            )
        }
        setContent {
            Projeto06Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FinalSpaceChars(viewModel)
                }
            }
        }
    }
}

@Composable
fun FinalSpaceChars(
    viewModel: CharactersViewModel
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar (
                modifier = Modifier
                    .height(65.dp)
                    .background(Color(0xFF1D2790))
                    .padding(bottom = 15.dp)
            ){
                BottomNavigationItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1A237E)),
                    selected = true,
                    icon = { Icons.Default.Check},
                    label = { Text(text = "Home", color = Color(0xFFFFFFFF), fontWeight = FontWeight.Bold)},
                    onClick = { navController.navigate("charslistscreen") }

                )
                BottomNavigationItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1A237E)),
                    selected = true,
                    icon = { Icons.Default.Check },
                    label = { Text(text = "Details", color = Color(0xFFFFFFFF)) },
                    onClick = { navController.navigate("charsdetailslistscreen") }

                )
            }
        }

    ) {
        CharactersListScreen(charactersViewModel = viewModel)
        CharactersDetailsListScreen(charactersViewModel = viewModel)
        NavHost(
            navController = navController,
            startDestination = "charslistscreen",
        ){
            composable("charslistscreen"){
                CharactersListScreen(charactersViewModel = viewModel)
            }
            composable("charsdetailslistscreen"){
                CharactersDetailsListScreen(charactersViewModel = viewModel)
            }
        }
    }


}
