package com.example.qtube

import androidx.recyclerview.widget.DiffUtil

class VideoDiffutil( val oldList:List<Video>, val newList:List<Video>): DiffUtil.Callback(){
    override fun getOldListSize()=oldList.size
    override fun getNewListSize()=newList.size


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (
                oldList[oldItemPosition].texttitle==newList[newItemPosition].texttitle
                        && oldList[oldItemPosition].texttime==newList[newItemPosition].texttime
                )
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

}