package com.example.appnicolas.network

import com.example.appnicolas.data.model.response.TeamObjectResponse
import com.example.appnicolas.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HttpService {

    private val retrofit = RetrofitHelper.getRestrofit()

    suspend fun getTeams(leagueName: String): TeamObjectResponse{

        return withContext(Dispatchers.IO){
            val response = retrofit.create(APIService::class.java).getTeamsByLeague(leagueName)
              response.body() ?: TeamObjectResponse(teams = arrayListOf())
        }

    }
}