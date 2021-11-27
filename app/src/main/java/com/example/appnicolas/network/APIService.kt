package com.example.appnicolas.network

import com.example.appnicolas.data.model.response.EventResponse
import com.example.appnicolas.data.model.response.TeamObjectResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
    suspend fun getTeamsByLeague(@Url url: String) : Response<TeamObjectResponse>

    @GET
    fun getEventsByTeamId(@Url url: String) : Response<EventResponse>
}