package com.example.appnicolas.utils

import com.example.appnicolas.data.model.League

object BurnedData {

    val appLeagueList: List<League> = listOf<League>(
        League(name = "Spanish league", nameQuery = "Spanish%20La%20Liga" , id= 0),
        League(name = "English league", nameQuery = "English%20Premier%20League" , id= 0),
        League(name = "Copa America", nameQuery = "Copa%20America" , id= 0),
    )

}