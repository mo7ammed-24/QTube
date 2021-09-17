package com.example.qtube.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jsonparser.data.Items
import com.example.qtube.R
import com.example.qtube.data.DataManager
import com.example.qtube.data.domain.Feeds
import com.example.qtube.databinding.ItemVideoBinding
import com.example.qtube.ui.adapters.listener.VideoIntectionListener

class VideoAdapter(private var itemList: List<Items>, val listener: VideoIntectionListener):RecyclerView.Adapter<VideoAdapter.VideoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_video,parent,false)
        return VideoHolder(view)
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        val currentVideo = itemList[position]

        holder.apply {
            binding.title.text = currentVideo.title
            binding.time.text = DataManager.showTimeFormat(durationInMins = currentVideo.duration!!)
            Glide.with(binding.root).load(currentVideo.art).into(binding.mainImage)
            Glide.with(binding.root).load(currentVideo.art).into(binding.profile)

            binding.root.setOnClickListener(View.OnClickListener {
                listener.onclickVideoItem(currentVideo)
            })
        }
    }

    override fun getItemCount(): Int = itemList.size

    class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemVideoBinding.bind(itemView)
    }


    fun updateData(newList:List<Items>) {
        val diffrentResult = DiffUtil.calculateDiff(VideoDiffutil(itemList,newList))
        itemList = newList
        diffrentResult.dispatchUpdatesTo(this)
    }
}