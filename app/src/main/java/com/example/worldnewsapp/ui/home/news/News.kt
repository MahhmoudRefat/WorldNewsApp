package com.example.worldnewsapp.ui.home.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.worldnewsapp.api.ApiManager
import com.example.worldnewsapp.api.model.sourcesResponse.SourcesResponse
import com.example.worldnewsapp.databinding.FragmentNewsBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class News : Fragment() {
    lateinit var  viewbinding : FragmentNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewbinding = FragmentNewsBinding.inflate(inflater,container,false)
        return viewbinding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getNewsSources()
    }

    private fun getNewsSources() {
        ApiManager.getServices().getNewsSources().enqueue(object :retrofit2.Callback<SourcesResponse>{
            override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<SourcesResponse>,
                response: Response<SourcesResponse>
            ) {
                TODO("Not yet implemented")
            }

        })
    }

}