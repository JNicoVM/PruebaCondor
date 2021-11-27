package com.example.appnicolas.data.model.response

import com.google.gson.annotations.SerializedName

data class EventResponse(
    @SerializedName("idEvent") val id: String,
    @SerializedName("strEvent")val name: String,
    @SerializedName("strLeague")val league: String,
    @SerializedName("dateEvent")val dateStr: String,
    @SerializedName("strTime")val timeStr: String,
    @SerializedName("strCountry")val country: String,
    @SerializedName("strVenue")val venue: String
)