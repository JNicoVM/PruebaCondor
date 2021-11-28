package com.example.appnicolas.data.repository

import com.example.appnicolas.dao.FavoriteDao
import com.example.appnicolas.data.entities.Favorite
import com.example.appnicolas.data.model.response.EventObjectResponse
import com.example.appnicolas.data.model.response.TeamObjectResponse
import com.example.appnicolas.network.HttpService
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val api: HttpService,
    private val leagueDao: FavoriteDao
) {

    suspend fun getTeams(leagueQuery:String): TeamObjectResponse {
        return api.getTeams("search_all_teams.php?l=$leagueQuery")
    }

    suspend fun getEvents(teamId: String): EventObjectResponse {
        return api.getEvents("eventslast.php?id=$teamId")
    }

    suspend fun getFavoritesInDB(): List<Favorite> {
        return leagueDao.getAll()
    }

    suspend fun insertFavoritesInDB(favorites:List<Favorite>) {
         leagueDao.insert(favorites)
    }
}