package com.example.appnicolas.ui.activities.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnicolas.data.model.response.TeamResponse
import com.example.appnicolas.domain.GetTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTeamsUseCase: GetTeamsUseCase
) : ViewModel() {

    val teamList = MutableLiveData<ArrayList<TeamResponse>>()
    private var _localTeamList = ArrayList<TeamResponse>()
    val localTeamList : ArrayList<TeamResponse>
        get() = _localTeamList
    //Loading Screen
    val isLoading = MutableLiveData<Boolean>()
    //Error data
    val isError = MutableLiveData<Boolean>()


    fun onCreate(){
        viewModelScope.launch {
            val result = getTeamsUseCase()
            if(!result.teams.isNullOrEmpty()){
                _localTeamList.addAll(result.teams?: arrayListOf())
                teamList.postValue(_localTeamList)
            }else{
                isError.postValue(true)
            }

            isLoading.postValue(false)
        }
    }
}