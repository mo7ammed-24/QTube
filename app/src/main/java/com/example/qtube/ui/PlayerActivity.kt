package com.example.qtube.ui

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qtube.R
import com.example.qtube.util.Constants
import com.example.qtube.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
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

        val dataSourceFactory = DefaultDataSourceFactory(this, "sample")
        val mediaSource =  ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(videoURL)))
        player?.setMediaSource(mediaSource)
        player?.prepare()
    }

    override fun onStop() {
        super.onStop()
        player?.release()
    }
}