package com.example.appnicolas.db

import com.example.appnicolas.data.entities.Favorite
import com.example.appnicolas.data.repository.AppRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ProjectDBTest  {

 @Test
 fun writeAndReadSpend(repository: AppRepository){
     runBlocking {
         val favorite = Favorite(0,"prueba", "prueba")
         repository.insertFavoritesInDB(listOf(favorite))
        val favorites =  repository.getFavoritesInDB()
         Truth.assertThat(favorites.contains(favorite))
     }
 }

}