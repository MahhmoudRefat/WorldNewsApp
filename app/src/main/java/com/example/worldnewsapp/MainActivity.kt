package com.example.worldnewsapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.worldnewsapp.databinding.ActivityMainBinding
import com.example.worldnewsapp.ui.home.newsSources.NewsSourcesFragment

class MainActivity : AppCompatActivity() {
    lateinit var viewbinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)


        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_main,NewsSourcesFragment()).commit()


    }
}