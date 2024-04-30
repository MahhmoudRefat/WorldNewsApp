package com.example.worldnewsapp.ui.newsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.worldnewsapp.api.ApiManager
import com.example.worldnewsapp.api.model.newsResponse.Article
import com.example.worldnewsapp.api.model.newsResponse.newsResponse
import com.example.worldnewsapp.api.model.sourcesResponse.Source
import com.example.worldnewsapp.api.model.sourcesResponse.SourcesResponse
import com.example.worldnewsapp.databinding.FragmentNewsBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsFragment : Fragment() {

    lateinit var viewBinding: FragmentNewsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNewsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    var source: Source? = null
    fun changesource(source: Source) {
        this.source = source

        loadNews()

    }

    private fun loadNews() {


        changeLoadingVisibility(true)
        source?.id?.let {sourceId->
            ApiManager.getServices().getNews(source = sourceId).enqueue(object : Callback<newsResponse> {
                override fun onFailure(call: Call<newsResponse>, t: Throwable) {
                    showError(t.message)
                    changeLoadingVisibility(false)
                }

                override fun onResponse(
                    call: Call<newsResponse>,
                    response: Response<newsResponse>
                ) {
                    changeLoadingVisibility(false)
                    if (response.isSuccessful) {
                        showNewsList(response.body()?.articles)
                        return

                    } else {
                        val responseJson = response.errorBody()?.string()
                        val errorResponse =
                            Gson().fromJson(responseJson, newsResponse::class.java)
                        showError(errorResponse.message)
                    }
                }
            })
        }
    }

    val adapter = NewsAdapter(null)
    private fun showNewsList(articles: List<Article>?) {
        adapter.changeData(articles)
    
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initviews()
    }

    private fun initviews() {
        viewBinding.newsRecycler.adapter = adapter
    }

    fun changeLoadingVisibility(isLoadingVisible: Boolean) {
        viewBinding.pbLoading.isVisible = isLoadingVisible
    }
    private fun showError(message: String?) {
        viewBinding.errorView.isVisible = true
        viewBinding.tvErrorMessage.text = message
    }
}

