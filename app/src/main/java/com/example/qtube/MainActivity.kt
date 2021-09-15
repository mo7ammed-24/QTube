package com.example.qtube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.qtube.data.DataManager
import com.example.qtube.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() ,VideoIntectionListener{
    lateinit var binding:ActivityMainBinding
    lateinit var adapter:VideoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    fun setup(){
        runOnUiThread{
            DataManager.parser("https://raw.githubusercontent.com/Bareq-altaamah/mock/main/classic.json")
        }
        adapter = VideoAdapter(DataManager.items,this)
        binding?.recyclerView.adapter = adapter

    }
    override fun onclicktitle(name: String) {
        Toast.makeText(applicationContext,name,Toast.LENGTH_SHORT).show()
    }
}