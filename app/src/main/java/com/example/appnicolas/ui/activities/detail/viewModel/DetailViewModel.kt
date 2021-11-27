package com.example.appnicolas.ui.activities.detail.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnicolas.data.model.response.TeamResponse
import com.example.appnicolas.domain.GetTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getTeamsUseCase: GetTeamsUseCase
) : ViewModel() {

    val teamList = MutableLiveData<ArrayList<TeamResponse>>()
    var localTeamList = ArrayList<TeamResponse>()
    //Loading Screen
    val isLoading = MutableLiveData<Boolean>()
    //Error data
    val isError = MutableLiveData<Boolean>()


    fun onCreate(){
        viewModelScope.launch {
            val result = getTeamsUseCase()
            if(!result.teams.isNullOrEmpty()){
                localTeamList.addAll(result.teams?: arrayListOf())
                teamList.postValue(localTeamList)
            }else{
                isError.postValue(true)
            }

            isLoading.postValue(false)
        }
    }
}