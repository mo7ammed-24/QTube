package com.example.qtube.data.domain

import com.example.jsonparser.data.Items
import com.google.gson.annotations.SerializedName

data class Feeds (
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("items") val items: List<Items>?
)