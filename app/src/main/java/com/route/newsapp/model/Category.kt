package com.route.newsapp.model

import com.route.newsapp.R

data class Category (
    val id : String,
    val title:String,
    val image:Int,
    val color:Int
){
    companion object{
        val categories = listOf<Category>(
            Category(id = "sports", title = "Sports", image = R.drawable.ic_sports, color = R.color.red),
            Category(id = "entertainment", title = "entertainment", image = R.drawable.ic_politics, color = R.color.blue),
            Category(id = "health", title = "Health", image = R.drawable.ic_health, color = R.color.pink),
            Category(id = "business", title = "Business", image = R.drawable.ic_business, color = R.color.deep_orange),
            Category(id = "technology", title = "Technology", image = R.drawable.ic_enviroment, color = R.color.light_blue),
            Category(id = "science", title = "Science", image = R.drawable.ic_science, color = R.color.yellow)

        )
    }
}