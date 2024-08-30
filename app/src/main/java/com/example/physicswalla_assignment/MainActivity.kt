package com.example.physicswalla_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.physicswalla_assignment.ui.characterDetailsScreenPackage.CharacterDetailScreen
import com.example.physicswalla_assignment.ui.homePackage.CharacterListScreen
import com.example.physicswalla_assignment.ui.homePackage.CharacterViewModel
import com.example.physicswalla_assignment.ui.theme.PhysicsWalla_AssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<CharacterViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhysicsWalla_AssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                        CharacterListScreen(viewModel = viewModel)

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "characterList") {
                        composable("characterList") {
                            // Passing viewModel directly from HiltViewModel to the composable
//                            val viewModel: CharacterViewModel = hiltViewModel()
                            CharacterListScreen( navController = navController,viewModel = viewModel)
                        }
                        composable("characterDetail/{characterId}") { backStackEntry ->
                            val characterId = backStackEntry.arguments?.getString("characterId")
                            // Pass the characterId to the detail screen
                            CharacterDetailScreen(characterId = characterId,viewModel)
                        }
                    }


                }
            }
        }
    }







}
