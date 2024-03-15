package com.route.newsapp.data.api.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class SourcesResponse(

    @field:SerializedName("sources")
    val sources: List<Source?>? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("message")
    val message: String? = null
)

@Entity
@Parcelize
data class Source(

    @field:SerializedName("country")
    val country: String? = null,

    @ColumnInfo
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("language")
    val language: String? = null,

    @PrimaryKey
    @ColumnInfo
    @field:SerializedName("id")
    val id: String,

    @ColumnInfo
    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("url")
    val url: String? = null
) : Parcelable
