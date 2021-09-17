package com.example.qtube.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.jsonparser.data.Items

class VideoDiffutil(val oldList: List<Items>, val newList: List<Items>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (
                oldList[oldItemPosition].title == newList[newItemPosition].title
                        && oldList[oldItemPosition].duration == newList[newItemPosition].duration
                )
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}