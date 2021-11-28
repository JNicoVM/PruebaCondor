package com.example.appnicolas.ui.activities.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnicolas.data.entities.Favorite
import com.example.appnicolas.data.model.response.TeamResponse
import com.example.appnicolas.domain.GetFavoritesFromDbUseCase
import com.example.appnicolas.domain.GetTeamsUseCase
import com.example.appnicolas.domain.SetFavoritesToDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTeamsUseCase: GetTeamsUseCase,
    private val getFavoritesFromDbUseCase: GetFavoritesFromDbUseCase
    ,
    private val setFavoritesToDbUseCase: SetFavoritesToDbUseCase
) : ViewModel() {

    val teamList = MutableLiveData<ArrayList<TeamResponse>>()
    private var _localTeamList = ArrayList<TeamResponse>()
    val localTeamList : ArrayList<TeamResponse>
        get() = _localTeamList

    val favoriteList = MutableLiveData<ArrayList<Favorite>>()
    private var _localfavoriteList = ArrayList<Favorite>()
    val localfavoriteList : ArrayList<Favorite>
        get() = _localfavoriteList

    //Loading Screen
    val isLoading = MutableLiveData<Boolean>()
    //Error data
    val isError = MutableLiveData<Boolean>()


    fun onCreate(){
        viewModelScope.launch {
            getFavoritesFromDbUseCase.invoke()
        }
    }

    fun getTeamsByLeague(leagueQuery:String){
        isLoading.postValue(true)
        viewModelScope.launch {
            getFavorites()
            val result = getTeamsUseCase(leagueQuery)
            if(!result.teams.isNullOrEmpty()){
                _localTeamList.addAll(result.teams)
                teamList.postValue(_localTeamList)
            }else{
                isError.postValue(true)
            }

            isLoading.postValue(false)
        }
    }

    fun cleanTeamsList(){
        _localTeamList.clear()
        teamList.postValue(_localTeamList)
    }

    fun insertFavorite(teamResponse: TeamResponse){
        val favorite = Favorite(id = 0, name = teamResponse.name?:"", teamId = teamResponse.id?:"" )
        viewModelScope.launch {
            setFavoritesToDbUseCase.invoke(listOf(favorite))
        }
    }

     fun getFavorites(){
        viewModelScope.launch {
            _localfavoriteList.clear()
           _localfavoriteList.addAll(ArrayList(getFavoritesFromDbUseCase.invoke()))
           favoriteList.postValue(_localfavoriteList)
        }
    }
}