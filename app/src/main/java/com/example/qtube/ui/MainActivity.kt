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
import com.example.qtube.util.Probarty
import android.annotation.SuppressLint

import com.google.android.material.chip.ChipGroup




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
        runOnUiThread{
            DataManager.parser(Constants.mainUrl)

        }
        adapter = VideoAdapter(DataManager.items, this)
        binding.recyclerView.adapter = adapter

       // chipGroupFunction()
    }

    @SuppressLint("WrongViewCast")

    private fun chipGroupFunction() {
  //      val chipGroup = binding.chipGroup
//        val checkedChipId = chipGroup.checkedChipId // Returns View.NO_ID if singleSelection = false
//        val checkedChipIds = chipGroup.checkedChipIds // Returns a list of the selected chips' IDs, if any

        val chipGroup = findViewById<ChipGroup>(R.id.chip_group)
        chipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.all -> adapter.updateData(DataManager.sortFeedsBy(Probarty.Year))
                R.id.year -> adapter.updateData(DataManager.sortFeedsBy(Probarty.Year))
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