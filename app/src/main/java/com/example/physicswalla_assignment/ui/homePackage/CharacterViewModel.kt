package com.example.physicswalla_assignment.ui.homePackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.physicswalla_assignment.models.AnimeCharacterDetailsResponseModel
import com.example.physicswalla_assignment.models.CharacterApiResponseModel
import com.example.physicswalla_assignment.repository.CharacterRepository
import com.example.physicswalla_assignment.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val  apiRepository:CharacterRepository):ViewModel(){

    fun getAllCharacter(){

        viewModelScope.launch {
            apiRepository.getCharacterList()
        }

    }

    val getCharacterList:LiveData<NetworkResult<CharacterApiResponseModel>?> get() = apiRepository.characterList



    fun getCharacterDetails(characterId:String){

        viewModelScope.launch {
            apiRepository.getCharacterDetails(characterId)
        }

    }

    val getCharacterDetails:LiveData<NetworkResult<AnimeCharacterDetailsResponseModel>?> get() = apiRepository.characterDetail




}
