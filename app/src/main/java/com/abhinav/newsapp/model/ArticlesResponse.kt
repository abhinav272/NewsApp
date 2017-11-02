package com.abhinav.newsapp.ui.model
import com.google.gson.annotations.SerializedName


/**
 * Created by abhinav.sharma on 31/10/17.
 */


data class ArticlesResponse(
		@SerializedName("status") val status: String?, //ok
		@SerializedName("source") val source: String?, //abc-news-au
		@SerializedName("sortBy") val sortBy: String?, //top
		@SerializedName("articles") val articles: List<Article>?
)

data class Article(
		@SerializedName("author") val author: String?, //http://www.abc.net.au/news/5511636, http://www.abc.net.au/news/lucy-sweeney/7114718
		@SerializedName("title") val title: String?, //Senate President reignites citizenship debacle, says father may be British
		@SerializedName("description") val description: String?, //Liberal senator Stephen Parry tells the Government he may be a dual citizen, saying he has asked British authorities to examine his history.
		@SerializedName("url") val url: String?, //http://www.abc.net.au/news/2017-10-31/stephen-parry-tells-government-his-father-may-be-british/9104482
		@SerializedName("urlToImage") val urlToImage: String?, //http://www.abc.net.au/news/image/9105122-1x1-700x700.jpg
		@SerializedName("publishedAt") val publishedAt: String? //2017-10-31T07:02:48Z
)