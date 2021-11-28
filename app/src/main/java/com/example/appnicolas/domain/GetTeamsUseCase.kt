package com.example.appnicolas.domain

import com.example.appnicolas.data.model.response.TeamObjectResponse
import com.example.appnicolas.data.repository.AppRepository
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor( private val repository: AppRepository) {

    suspend operator fun invoke(leagueQuery:String): TeamObjectResponse =
        repository.getTeams(leagueQuery)

}