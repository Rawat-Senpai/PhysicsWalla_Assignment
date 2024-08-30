package com.example.physicswalla_assignment.repository

import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.physicswalla_assignment.api.AnimeApi
import com.example.physicswalla_assignment.models.AnimeCharacterDetailsResponseModel
import com.example.physicswalla_assignment.models.CharacterApiResponseModel
import com.example.physicswalla_assignment.utils.NetworkResult
import com.example.physicswalla_assignment.utils.safeApiCall
import javax.inject.Inject

class CharacterRepository  @Inject constructor(private val api: AnimeApi) {


    private val _characterList = MutableLiveData<NetworkResult<CharacterApiResponseModel>?>()
    val characterList : LiveData<NetworkResult<CharacterApiResponseModel>?> get() = _characterList


    private val _characterDetail = MutableLiveData<NetworkResult<AnimeCharacterDetailsResponseModel>?>()
    val characterDetail: LiveData<NetworkResult<AnimeCharacterDetailsResponseModel>?> = _characterDetail



    suspend fun getCharacterList(){

        _characterList.postValue(NetworkResult.Loading())
        val result = safeApiCall { api.getAllAnimeCharacter() }
        _characterList.postValue(result)

    }

    suspend fun getCharacterDetails(characterId:String){

        _characterDetail.postValue(NetworkResult.Loading())
        val result = safeApiCall { api.getAnimeCharacter(characterId) }
        _characterDetail.postValue(result)

    }






}