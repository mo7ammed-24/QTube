package com.example.qtube.data

import VideoResponse
import android.util.Log
import com.example.jsonparser.data.Items
import com.example.qtube.data.domain.Feeds
import com.example.qtube.util.Proberty
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

object DataManager {

    //Region initialize variables


    val videoList = mutableListOf<VideoResponse>()
    val videos: List<VideoResponse> get() = videoList


    val feedsList = mutableListOf<Feeds>()
    val feeds: List<Feeds> get() = feedsList

    var itemsList = mutableListOf<Items>()
    val items: List<Items> get() = itemsList

    val client = OkHttpClient()
    //End region

    fun addVideo(videoResponse: VideoResponse) {
        videoList.add(videoResponse)
    }

    fun addFeeds(feeds: Feeds) {
        feedsList.add(feeds)
    }

    fun addItems(items: Items) {
        itemsList.add(items)
    }

    //This function parse the data from json and store it in lists.
    fun parser(jsonUrl: String) {
        cleanJson(jsonUrl)
        val request = Request.Builder().url(jsonUrl).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //TODO Display lotti animation of 404 error or something XD.
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { jsonString ->
                    val response = Gson().fromJson(jsonString, VideoResponse::class.java)
                    //Loop through feeds to store object in feeds list.
                    response.feed?.forEach { addFeeds(it) }

                }
            }
        })
    }

    //This function clean the json from unwanted information
    private fun cleanJson(jsonString: String) {
        jsonString.replace("backgrounds", "").replace("ratings", "")
    }




    fun sortFeedsBy (property : Proberty) :List<Items>
            = when (property) {
        Proberty.ALL -> {
            feedsList[0].items as List<Items> + feedsList[1].items as List<Items> + feedsList[2].items as List<Items>
        }

        Proberty.HISTORICAL -> {
            feedsList[1].items as List<Items>
        }
        Proberty.M1910 -> {
            feedsList[2].items as List<Items>
        }
        Proberty.CHAPLIN -> {
            feedsList[3].items as List<Items>
        }


    }

}