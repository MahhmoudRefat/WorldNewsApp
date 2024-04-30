package com.example.worldnewsapp.ui.newsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.worldnewsapp.R
import com.example.worldnewsapp.api.model.newsResponse.Article
import com.example.worldnewsapp.databinding.ItemNewsArticleBinding

class NewsAdapter(var newsList:List<Article?>?) :Adapter<NewsAdapter.NewsViewHolder>(){
    class NewsViewHolder(val itemBinding : ItemNewsArticleBinding):ViewHolder(itemBinding.root){
        fun bind(news: Article?) {
            itemBinding.articleTitle.text=news?.title
            itemBinding.articleSource.text=news?.author
            itemBinding.articlePublishAt.text=news?.formatDurationFromNow()
            Glide.with(itemView).load(news?.urlToImage).placeholder(R.drawable.ic_launcher_background).into(itemBinding.articleImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding = ItemNewsArticleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  NewsViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = newsList?.size?:0
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            holder.bind(newsList?.get(position))
    }

    fun changeData(articles: List<Article>?) {
        newsList = articles
        notifyDataSetChanged()
    }
}