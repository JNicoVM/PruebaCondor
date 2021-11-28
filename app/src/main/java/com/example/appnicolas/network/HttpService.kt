package com.example.appnicolas.network

import com.example.appnicolas.data.model.response.EventObjectResponse
import com.example.appnicolas.data.model.response.TeamObjectResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HttpService @Inject constructor(private val api: APIService) {

    suspend fun getTeams(leagueQuery: String): TeamObjectResponse{
        return withContext(Dispatchers.IO){
            val response = api.getTeamsByLeague(leagueQuery)
              response.body() ?: TeamObjectResponse(teams = arrayListOf())
        }
    }

    suspend fun getEvents(teamQuery: String): EventObjectResponse{
        return withContext(Dispatchers.IO){
            val response = api.getEventsByTeam(teamQuery)
            response.body() ?: EventObjectResponse(results = arrayListOf())
        }
    }
}