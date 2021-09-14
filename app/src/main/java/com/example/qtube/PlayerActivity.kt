package com.example.qtube

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qtube.databinding.ActivityMainBinding
import com.example.qtube.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class PlayerActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityPlayerBinding

    private var player: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        initializePlayer()

    }

    private fun initializePlayer() {
        player = SimpleExoPlayer.Builder(this).build()
        _binding.exoPlayerVideo.player = player

        val videoUrl = "https://android-tv-classics.firebaseapp.com/content/le_voyage_dans_la_lun/media_le_voyage_dans_la_lun.mp4"
        val dataSourceFactory = DefaultDataSourceFactory(this, "sample")
        val mediaSource =  ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(videoUrl)))
        player?.setMediaSource(mediaSource)
        player?.prepare()
    }


    override fun onResume() {
        super.onResume()
        player?.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        player?.playWhenReady = false
        if (isFinishing) {
            player?.release()
        }
    }
}