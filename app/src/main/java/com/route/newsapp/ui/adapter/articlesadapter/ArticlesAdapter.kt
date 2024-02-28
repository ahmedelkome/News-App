package com.route.newsapp.ui.adapter.articlesadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.route.newsapp.api.models.Article
import com.route.newsapp.databinding.ArticleItemBinding

class ArticlesAdapter(var articles:List<Article?>?) : Adapter<ArticlesAdapter.ArticleViewHolder>() {

    var onArticleClick: onClickArticle?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        val binding = ArticleItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int = articles!!.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles?.get(position)
        holder.binding.apply {
            sourceTv.text = article?.source?.name
            articleTitle.text = article?.title
            articleTime.text = article?.publishedAt
            Glide.with(root).load(article?.urlToImage).into(holder.binding.articleImage)
            holder.binding.root.setOnClickListener {
                onArticleClick?.onitemclick(article!!)
            }
        }
    }

    fun updateArticles(newList:List<Article?>?){
        articles = newList
        notifyDataSetChanged()
    }
    interface onClickArticle {
        fun onitemclick(article:Article)
    }

    class ArticleViewHolder(val binding: ArticleItemBinding) : ViewHolder(binding.root){

    }
}