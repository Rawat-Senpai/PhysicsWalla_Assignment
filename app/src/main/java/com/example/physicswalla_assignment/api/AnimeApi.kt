package com.example.physicswalla_assignment.api

import com.example.physicswalla_assignment.models.AnimeCharacterDetailsResponseModel
import com.example.physicswalla_assignment.models.CharacterApiResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi {

    @GET("character")
    suspend fun getAllAnimeCharacter(): Response<CharacterApiResponseModel>

    @GET("character/{id}")
    suspend fun getAnimeCharacter(
        @Path("id") characterId: String,
    ): Response<AnimeCharacterDetailsResponseModel>

}