package com.example.mykotlinapp.model.dto

data class NewsResponceDTO (
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDTO>
)

data class ArticleDTO(
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String
)