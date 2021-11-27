package com.example.appnicolas.data.repository

import com.example.appnicolas.data.model.response.TeamObjectResponse
import com.example.appnicolas.network.HttpService
import javax.inject.Inject

class AppRepository @Inject constructor(private val api: HttpService) {

    suspend fun getTeams():TeamObjectResponse{
        return api.getTeams("search_all_teams.php?l=Spanish%20La%20Liga")
    }
}