package com.example.appnicolas.ui.activities.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnicolas.data.model.League
import com.example.appnicolas.data.model.response.TeamResponse
import com.example.appnicolas.domain.GetTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTeamsUseCase: GetTeamsUseCase,
) : ViewModel() {

    val teamList = MutableLiveData<ArrayList<TeamResponse>>()
    private var _localTeamList = ArrayList<TeamResponse>()
    val localTeamList : ArrayList<TeamResponse>
        get() = _localTeamList

    val leagueList = MutableLiveData<ArrayList<League>>()
    private var _localLeagueList = ArrayList<League>()
    val localLeagueList : ArrayList<League>
        get() = _localLeagueList

    //Loading Screen
    val isLoading = MutableLiveData<Boolean>()
    //Error data
    val isError = MutableLiveData<Boolean>()


    fun onCreate(){
//        val tempoLeagueList = listOf<League>(League(name = "jo", nameQuery = "jo_jo" , id= 0))
        viewModelScope.launch {
//            setLeaguesToDbUseCase.invoke(tempoLeagueList)
//            val resultDb =  getLeaguesFromDbUseCase.invoke()
//            leagueList.postValue(ArrayList(resultDb))
            val result = getTeamsUseCase("Spanish%20La%20Liga")
            if(!result.teams.isNullOrEmpty()){
                _localTeamList.addAll(result.teams)
                teamList.postValue(_localTeamList)
            }else{
                isError.postValue(true)
            }

            isLoading.postValue(false)
        }
    }

    fun getTeamsByLeague(leagueQuery:String){
        isLoading.postValue(true)
        viewModelScope.launch {
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
}