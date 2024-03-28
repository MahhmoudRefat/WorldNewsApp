package com.example.worldnewsapp.ui.home.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.worldnewsapp.api.ApiManager
import com.example.worldnewsapp.api.model.sourcesResponse.Source
import com.example.worldnewsapp.api.model.sourcesResponse.SourcesResponse
import com.example.worldnewsapp.databinding.FragmentNewsBinding
import com.google.gson.Gson
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
                showError(t.message)
            }

            override fun onResponse(
                call: Call<SourcesResponse>,
                response: Response<SourcesResponse>
            ) {
                if(response.isSuccessful){
                    showNewsSources(response.body()?.sources)
                }
                val responseJson = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(responseJson,SourcesResponse::class.java)
            showError(errorResponse.message)

            }

        })
    }

    private fun showNewsSources(sources: List<Source>?) {

        viewbinding.errorView.isVisible=false
        viewbinding.pbLoading.isVisible=false
        sources?.forEach{source ->
            val tab = viewbinding.tabLayout.newTab()
            tab.text = source.name
            viewbinding.tabLayout.addTab(tab)
        }
    }

    private fun showError(message: String?) {
       viewbinding.errorView.isVisible = true
        viewbinding.tvErrorMessage.text=message
    }

}