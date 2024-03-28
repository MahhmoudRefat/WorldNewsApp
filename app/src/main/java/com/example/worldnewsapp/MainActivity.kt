package com.example.worldnewsapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.worldnewsapp.databinding.ActivityMainBinding
import com.example.worldnewsapp.ui.home.news.News

class MainActivity : AppCompatActivity() {
    lateinit var viewbinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)


        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,News()).commit()


    }
}