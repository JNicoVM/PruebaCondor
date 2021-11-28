package com.example.appnicolas.domain

import com.example.appnicolas.data.entities.Favorite
import com.example.appnicolas.data.repository.AppRepository
import javax.inject.Inject

class GetFavoritesFromDbUseCase @Inject constructor(private val repository: AppRepository) {

    suspend operator fun invoke(): List<Favorite> =
        repository.getFavoritesInDB()

}