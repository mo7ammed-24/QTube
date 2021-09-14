package com.example.qtube.data

import VideoResponse
import android.util.Log
import com.example.jsonparser.data.Items
import com.example.qtube.data.domain.Feeds
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

object DataManager {

    //Region initialize variables

    val videoList = mutableListOf<VideoResponse>()
    val videos :List<VideoResponse> get() = videoList

    val feedsList = mutableListOf<Feeds>()
    val feeds:List<Feeds> get() = feedsList

    val itemsList = mutableListOf<Items>()
    val items: List<Items> get() = itemsList

    val client = OkHttpClient()
    //End region

    private fun addVideo(videoResponse: VideoResponse) : VideoResponse{
        videoList.add(videoResponse)
        return VideoResponse(feeds)
    }
    fun addFeeds(feeds: Feeds){
        feedsList.add(feeds)
    }
    fun addItems(items: Items){
        itemsList.add(items)
    }

    //This function parse the data from json and store it in lists.
    fun parser(jsonUrl: String) : List<VideoResponse> {
        cleanJson(jsonUrl)
        val request = Request.Builder().url(jsonUrl).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                //TODO Display lotti animation of 404 error or something XD.
            }
            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { jsonString ->
                    val response = Gson().fromJson(jsonString,VideoResponse::class.java)

                    val idFeed = response.feed?.joinToString { it.id.toString() }
                    val titleFeed = response.feed?.joinToString { it.title.toString() }
                    val descriptionFeed = response.feed?.joinToString { it.description.toString() }
                    val imageFeed = response.feed?.joinToString { it.image.toString() }

                    val itemId = response.feed?.joinToString { it.items?.joinToString { it.id.toString() }.toString() }
                    val itemTitle = response.feed?.joinToString { it.items?.joinToString { it.title.toString() }.toString() }
                    val itemDescription = response.feed?.joinToString { it.items?.joinToString { it.description.toString() }.toString() }
                    val itemDirector = response.feed?.joinToString { it.items?.joinToString { it.director.toString() }.toString() }
                    val itemUrl = response.feed?.joinToString { it.items?.joinToString { it.url.toString() }.toString() }
                    val itemYear= response.feed?.joinToString { it.items?.joinToString { it.year.toString() }.toString() }
                    val itemDuration= response.feed?.joinToString { it.items?.joinToString { it.duration.toString() }.toString() }
                    val itemArt = response.feed?.joinToString { it.items?.joinToString { it.art.toString() }.toString() }

                    addItems(Items(itemId,itemYear,itemDuration,itemTitle,itemDirector,itemDescription,itemUrl,itemArt))
                    addFeeds(Feeds(idFeed,titleFeed,descriptionFeed,imageFeed, items))
                    addVideo(VideoResponse(feeds))

                    Log.i("testTag", videos.toString())
                }
            }
        })
        return videos
    }

    //This function clean the json from unwanted information
    private fun cleanJson(jsonString: String){
        jsonString.replace("backgrounds","").replace("ratings","")
    }

    fun itemsSize() = items.size
    fun feedsSize() = feeds.size
    fun videosSize() = videos.size


}