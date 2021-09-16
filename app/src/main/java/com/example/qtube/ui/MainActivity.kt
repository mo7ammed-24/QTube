package com.example.qtube.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jsonparser.data.Items
import com.example.qtube.util.Constants
import com.example.qtube.data.DataManager
import com.example.qtube.databinding.ActivityMainBinding
import com.example.qtube.ui.adapters.VideoAdapter
import com.example.qtube.ui.adapters.listener.VideoIntectionListener

class MainActivity : AppCompatActivity() , VideoIntectionListener {
    lateinit var binding:ActivityMainBinding
    lateinit var adapter: VideoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    fun setup(){
        runOnUiThread{
            DataManager.parser(Constants.mainUrl)
        }
        adapter = VideoAdapter(DataManager.items,this)
        binding.recyclerView.adapter = adapter

    }

    override fun onclickVideoItem(videoItem: Items) {
        val intent = Intent(this, PlayerActivity::class.java)
        intent.apply {
            putExtra(Constants.VIDEO_URL, videoItem.url)
            putExtra(Constants.VIDEO_TITLE, videoItem.title)
        }

        startActivity(intent)
    }
}