package com.example.worldnewsapp.ui.newsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter.ViewBinder
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.example.worldnewsapp.R
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


class newsFragment : Fragment() {

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
        source?.id?.let {
            ApiManager.getServices().getNews(source = it).enqueue(object : Callback<newsResponse> {
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
                        showNewsSources(response.body()?.articles)

                    } else {
                        val responseJson = response.errorBody()?.string()
                        val errorResponse =
                            Gson().fromJson(responseJson, SourcesResponse::class.java)
                        showError(errorResponse.message)
                    }
                }
            })
        }
    }

    private fun showNewsSources(articles: List<Article>?) {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    fun changeLoadingVisibility(isLoadingVisible: Boolean) {
        viewBinding.pbLoading.isVisible = isLoadingVisible
    }
    private fun showError(message: String?) {
        viewBinding.errorView.isVisible = true
        viewBinding.tvErrorMessage.text = message
    }
}

