package com.example.worldnewsapp.ui.home.newsSources

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.worldnewsapp.R
import com.example.worldnewsapp.api.ApiManager
import com.example.worldnewsapp.api.model.sourcesResponse.Source
import com.example.worldnewsapp.api.model.sourcesResponse.SourcesResponse
import com.example.worldnewsapp.databinding.FragmentNewsSourcesBinding
import com.example.worldnewsapp.ui.newsFragment.NewsFragment
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class NewsSourcesFragment : Fragment() {
    lateinit var viewbinding: FragmentNewsSourcesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewbinding = FragmentNewsSourcesBinding.inflate(inflater, container, false)
        return viewbinding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initviews()
        getNewsSources()
    }

        val newsFragment = NewsFragment()
    private fun initviews() {
        childFragmentManager.beginTransaction().replace(R.id.fragment_container,newsFragment).commit()

        viewbinding.btnTryAgain.setOnClickListener {
            viewbinding.errorView.isVisible = false
            getNewsSources()
        }
    }

    fun changeLoadingVisibility(isLoadingVisible: Boolean) {
        viewbinding.pbLoading.isVisible = isLoadingVisible
    }

     private fun getNewsSources() {


        //calling the api
        changeLoadingVisibility(true)
        ApiManager.getServices().getNewsSources()
            .enqueue(object : retrofit2.Callback<SourcesResponse> {
                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    showError(t.message)
                    changeLoadingVisibility(false)
                }

                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    changeLoadingVisibility(false)
                    if (response.isSuccessful) {
                        showNewsSources(response.body()?.sources)

                    } else {
                        val responseJson = response.errorBody()?.string()
                        val errorResponse =
                            Gson().fromJson(responseJson, SourcesResponse::class.java)
                        showError(errorResponse.message)
                    }

                }

            })
    }

    private fun showNewsSources(sources: List<Source>?) {

        viewbinding.errorView.isVisible = false
        viewbinding.pbLoading.isVisible = false
        sources?.forEach { source ->
            val tab = viewbinding.tabLayout.newTab()
            tab.text = source.name
            tab.tag = source
            viewbinding.tabLayout.addTab(tab)
        }
        viewbinding.tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                newsFragment.changesource(source)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                newsFragment.changesource(source)
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }
        })
        viewbinding.tabLayout.getTabAt(0)?.select()

    }

    private fun showError(message: String?) {
        viewbinding.errorView.isVisible = true
        viewbinding.tvErrorMessage.text = message
    }

}