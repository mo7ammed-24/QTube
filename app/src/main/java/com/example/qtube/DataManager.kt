package com.example.qtube

object DataManager {
    private val videList= mutableListOf<Video>()
    val video:List<Video>
        get()=videList.toList()

    private var videoIndex=0

    fun addvideo(video: Video){
        videList.add(video)
    }
    fun delvideo(index:Int){
        videList.removeAt(index)

    }
    fun getCurrentVideo():Video= videList[videoIndex]
    fun getNextvideo():Video{
        videoIndex++
        return videList[videoIndex]

    }
    fun getPrevoiusvideo():Video{
        videoIndex--
        return videList[videoIndex]
    }
}