package com.example.appnicolas.domain

import com.example.appnicolas.data.model.response.TeamObjectResponse
import com.example.appnicolas.data.repository.AppRepository

class GetTeamsUseCase {

    private val respository = AppRepository()

    suspend operator fun invoke(): TeamObjectResponse =
        respository.getTeams()

}