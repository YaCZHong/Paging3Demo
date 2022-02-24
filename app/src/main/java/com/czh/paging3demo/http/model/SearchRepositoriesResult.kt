package com.czh.paging3demo.http.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class SearchRepositoriesResult(
    val items: List<Repo>,
) : Parcelable

@Parcelize
data class Repo(
    val id: Int,
    val name: String,
    val description: String,
    @SerializedName("stargazers_count") var starCount: Int,
    val url: String
) : Parcelable