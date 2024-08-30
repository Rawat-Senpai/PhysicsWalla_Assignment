package com.example.physicswalla_assignment.ui.homePackage


import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.physicswalla_assignment.models.CharacterApiResponseModel
import com.example.physicswalla_assignment.models.Result
import com.example.physicswalla_assignment.ui.widgets.ErrorPopup
import com.example.physicswalla_assignment.ui.widgets.LayoutCharacterItem
import com.example.physicswalla_assignment.ui.widgets.ProgressBar
import com.example.physicswalla_assignment.utils.NetworkResult


@Composable

fun CharacterListScreen(navController:NavController,viewModel: CharacterViewModel) {

    val characterListState by viewModel.getCharacterList.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllCharacter()
    }


    when(characterListState){
        is NetworkResult.Error -> {

            Log.d("checking",characterListState?.message.toString())
            val errorMessage = (characterListState as NetworkResult.Error).message
            ErrorPopup(message = errorMessage ?: "An unknown error occurred")
        }
        is NetworkResult.Loading -> {
            ProgressBar()
        }
        is NetworkResult.Success -> {

            Log.d("checking",characterListState?.data.toString())

            val characterList = (characterListState as NetworkResult.Success<CharacterApiResponseModel>).data?.results
            if (characterList != null) {
                LayoutCharacterList(characterList)

            }
        }
        null -> {}
    }



}

@Composable
fun LayoutCharacterList(characterList: List<Result>) {
    LazyColumn {
        items(characterList){it->
            LayoutCharacterItem(character = it)

        }
    }
}