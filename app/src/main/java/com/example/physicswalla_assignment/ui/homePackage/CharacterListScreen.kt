package com.example.physicswalla_assignment.ui.homePackage


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.physicswalla_assignment.R
import com.example.physicswalla_assignment.models.CharacterApiResponseModel
import com.example.physicswalla_assignment.models.Result
import com.example.physicswalla_assignment.ui.widgets.ErrorPopup
import com.example.physicswalla_assignment.ui.widgets.LayoutCharacterItem
import com.example.physicswalla_assignment.ui.widgets.ProgressBar
import com.example.physicswalla_assignment.utils.NetworkResult


@Composable
fun CharacterListScreen(navController: NavController, viewModel: CharacterViewModel) {

    val characterListState by viewModel.getCharacterList.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllCharacter()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Rick and Morty Characters",

            style = MaterialTheme.typography.headlineMedium.copy(
                fontFamily = FontFamily(Font(R.font.poppins_regular)) // Use Poppins font
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 14.dp)
        )

        when (characterListState) {
            is NetworkResult.Error -> {
                Log.d("checking", characterListState?.message.toString())
                val errorMessage = (characterListState as NetworkResult.Error).message
                ErrorPopup(message = errorMessage ?: "An unknown error occurred")
            }
            is NetworkResult.Loading -> {
                // ProgressBar()
            }
            is NetworkResult.Success -> {
                Log.d("checking", characterListState?.data.toString())
                val characterList = (characterListState as NetworkResult.Success<CharacterApiResponseModel>).data?.results
                if (characterList != null) {
                    LayoutCharacterList(characterList, navController)
                }
            }
            null -> {}
        }
    }
}


@Composable
fun LayoutCharacterList(characterList: List<Result>, navController: NavController) {
    LazyColumn {
        items(characterList){it->
            LayoutCharacterItem(character = it, onClick ={
                navController.navigate("characterDetail/${it.id}")
            })

        }
    }
}