package com.example.qtube

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(private var list: List<Video>,val listener:VideoIntectionListener):RecyclerView.Adapter<VideoViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
       val view= LayoutInflater.from(parent.context).inflate(R.layout.item_video,parent,false)
      return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
      val currentvideo=list[position]
        holder.apply {
          Title.text=currentvideo.texttitle
            Time1.text=currentvideo.texttime.toString()
            Option.text=currentvideo.textoption
            Title.setOnClickListener{
                listener.onclicktitle(currentvideo.texttitle)

            }
        }
    }
   fun setData(newList: List<Video>){
       val diffResult=DiffUtil.calculateDiff(VideoDiffutil(list,newList))
       list=newList
      diffResult.dispatchUpdatesTo(this)
   }
    override fun getItemCount()=list.size

}