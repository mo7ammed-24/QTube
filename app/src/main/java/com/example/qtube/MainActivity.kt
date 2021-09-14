package com.example.qtube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qtube.data.DataManager
import com.example.qtube.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        urlParser()
    }
    private fun urlParser() {
        runOnUiThread {
            DataManager.parser("https://raw.githubusercontent.com/Bareq-altaamah/mock/main/classic.json")
        }
    }
}