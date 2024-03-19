package com.route.newsapp.ui.screen

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.route.newsapp.R
import com.route.newsapp.constants.Constants
import com.route.newsapp.data.api.models.Article
import com.route.newsapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val article = intent.getParcelableExtra(Constants.ARTICLE_KEY, Article::class.java)
        binding.articleTime.text = article?.publishedAt
        binding.articleTitle.text = article?.description
        Glide.with(this).load(article?.urlToImage).into(binding.articleImage)
        binding.sourceTv.text = article?.source?.name
        binding.content.text = article?.content
        binding.sourceBtn.setOnClickListener {
            startActivity(Intent(Intent(Intent.ACTION_VIEW, Uri.parse("${article?.url}"))))
        }
    }
}