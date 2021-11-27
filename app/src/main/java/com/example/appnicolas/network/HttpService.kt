package com.example.appnicolas.network

import com.example.appnicolas.data.model.response.TeamObjectResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HttpService @Inject constructor(private val api: APIService) {

    suspend fun getTeams(leagueName: String): TeamObjectResponse{
        return withContext(Dispatchers.IO){
            val response = api.getTeamsByLeague(leagueName)
              response.body() ?: TeamObjectResponse(teams = arrayListOf())
        }

    }
}