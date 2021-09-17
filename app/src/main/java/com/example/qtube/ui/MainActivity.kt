package com.example.qtube.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jsonparser.data.Items
import com.example.qtube.R
import com.example.qtube.util.Constants
import com.example.qtube.data.DataManager
import com.example.qtube.databinding.ActivityMainBinding
import com.example.qtube.ui.adapters.VideoAdapter
import com.example.qtube.ui.adapters.listener.VideoIntectionListener
import com.example.qtube.util.Proberty
import android.annotation.SuppressLint
import android.util.Log


class MainActivity : AppCompatActivity() , VideoIntectionListener {
    lateinit var binding:ActivityMainBinding
    lateinit var adapter: VideoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_QTube)
        setContentView(binding.root)
        setup()
    }

    fun setup(){
            DataManager.parser(Constants.JSON_URL)
    Thread.sleep(20000)

        runOnUiThread {
            adapter = VideoAdapter(itemList = DataManager.sortFeedsBy(Proberty.ALL), this)
            binding.recyclerView.adapter = adapter
        }

        chipGroupFunction()
    }

    @SuppressLint("WrongViewCast")

    private fun chipGroupFunction() {
        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.all -> adapter.updateData(DataManager.sortFeedsBy(Proberty.ALL))
                R.id.historical -> adapter.updateData(DataManager.sortFeedsBy(Proberty.HISTORICAL))
                R.id.year -> adapter.updateData(DataManager.sortFeedsBy(Proberty.M1910))
                R.id.others -> adapter.updateData(DataManager.sortFeedsBy(Proberty.CHAPLIN))
            }
        }


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