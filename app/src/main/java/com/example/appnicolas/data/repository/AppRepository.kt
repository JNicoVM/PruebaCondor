package com.example.appnicolas.data.repository

import com.example.appnicolas.data.model.response.TeamObjectResponse
import com.example.appnicolas.network.HttpService

class AppRepository {

    private val api = HttpService()

    suspend fun getTeams():TeamObjectResponse{
        return api.getTeams("search_all_teams.php?l=English%20Premier%20League")

    }
}