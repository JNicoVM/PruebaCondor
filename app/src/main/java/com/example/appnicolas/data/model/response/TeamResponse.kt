package com.example.appnicolas.data.model.response

import com.google.gson.annotations.SerializedName

data class TeamObjectResponse(
    @SerializedName("teams") val teams: MutableList<TeamResponse>?,
)

data class TeamResponse(
    @SerializedName("idTeam") val id: String?,
    @SerializedName("strAlternate") val name: String?,
    @SerializedName("strDescriptionEN") val description: String?,
    @SerializedName("intFormedYear") val foundationYear: String?,
    @SerializedName("strTeamBadge") val imgBadge: String?,
    @SerializedName("strTeamJersey") val imgShirt: String?,
    @SerializedName("strYoutube")  val youtubeUrl: String?,
    @SerializedName("strWebsite") val website: String?,
    @SerializedName("strFacebook") val facebookUrl: String?,
    @SerializedName("strTwitter") val twitterUrl: String?,
    @SerializedName("strInstagram") val instagramUrl: String?,
)