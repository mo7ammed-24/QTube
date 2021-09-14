package com.example.jsonparser.data

import com.google.gson.annotations.SerializedName


data class Items (
    @SerializedName("id") val id: String?,
    @SerializedName("year") val year: String?,
    @SerializedName("duration") val duration: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("director") val director: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("art") val art: String?,
)