package com.example.qtube.ui

import android.content.Intent
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jsonparser.data.Items
import com.example.qtube.R
import com.example.qtube.data.DataManager
import com.example.qtube.util.Constants
import com.example.qtube.databinding.ActivityPlayerBinding
import com.example.qtube.ui.adapters.VideoAdapter
import com.example.qtube.ui.adapters.listener.VideoIntectionListener
import com.example.qtube.util.Proberty
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class PlayerActivity : AppCompatActivity(), VideoIntectionListener {

    private lateinit var binding: ActivityPlayerBinding
    lateinit var adapter: VideoAdapter
    private var player: SimpleExoPlayer? = null
    private var videoURL:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_QTube)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        videoURL = intent.getStringExtra(Constants.VIDEO_URL).toString()
        initializePlayer()
    }

    private fun initializePlayer() {
        player = SimpleExoPlayer.Builder(this).build()
        binding.videoPlayer.player = player
        binding.videoTitle.text = intent.getStringExtra(Constants.VIDEO_TITLE).toString()
        binding.videoDescription.text = intent.getStringExtra(Constants.VIDEO_DESCRIPTION).toString()
        val dataSourceFactory = DefaultDataSourceFactory(this, "sample")
        val mediaSource =  ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(videoURL)))
        player?.setMediaSource(mediaSource)
        player?.prepare()
        val newList = DataManager.returnVideoOfType(Proberty.ALL).filter {it.url!=videoURL  }.sortedBy{it.year}
        adapter = VideoAdapter(newList, this)
        binding.recyclerView.adapter = adapter
        player?.play()
    }

    override fun onStop() {
        super.onStop()
        player?.release()
    }

    override fun onclickVideoItem(videoItem: Items) {
        val intent = Intent(this, PlayerActivity::class.java)
        intent.apply {
            putExtra(Constants.VIDEO_URL, videoItem.url)
            putExtra(Constants.VIDEO_TITLE, videoItem.title)
            putExtra(Constants.VIDEO_DESCRIPTION,videoItem.description)
        }
        startActivity(intent)
    }}