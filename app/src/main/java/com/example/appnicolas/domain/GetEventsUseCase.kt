package com.example.appnicolas.domain

import com.example.appnicolas.data.model.response.EventObjectResponse
import com.example.appnicolas.data.model.response.TeamObjectResponse
import com.example.appnicolas.data.repository.AppRepository
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(private val repository: AppRepository) {

    suspend operator fun invoke(teamId: String): EventObjectResponse =
        repository.getEvents(teamId = teamId)
}