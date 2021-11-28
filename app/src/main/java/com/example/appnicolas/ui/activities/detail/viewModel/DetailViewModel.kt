package com.example.appnicolas.ui.activities.detail.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnicolas.data.model.response.EventResponse
import com.example.appnicolas.data.model.response.TeamResponse
import com.example.appnicolas.domain.GetEventsUseCase
import com.example.appnicolas.domain.GetTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {

    val eventList = MutableLiveData<ArrayList<EventResponse>>()
    var localEventList = ArrayList<EventResponse>()
    //Loading Screen
    val isLoading = MutableLiveData<Boolean>()
    //Error data
    val isError = MutableLiveData<Boolean>()


    fun onCreate(teamId: String){
        viewModelScope.launch{
        try {
            val result = getEventsUseCase(teamId =teamId)
            if(!result.results.isNullOrEmpty()){
                localEventList.addAll(result.results?: arrayListOf())
                eventList.postValue(localEventList)
            }else{
                isError.postValue(true)
            }
            isLoading.postValue(false)
        } catch (e: Exception){
            isError.postValue(true)
            isLoading.postValue(false)}
        }
    }
}