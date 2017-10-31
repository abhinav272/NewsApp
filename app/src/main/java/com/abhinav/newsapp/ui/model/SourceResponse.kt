package com.abhinav.newsapp.ui.model

import com.google.gson.annotations.SerializedName

/**
 * Created by abhinav.sharma on 31/10/17.
 */

data class SourceResponse(
        @SerializedName("status") val status: String?, //ok
        @SerializedName("sources") val sources: List<Source?>?
)

data class Source(
        @SerializedName("id") val id: String?, //abc-news-au
        @SerializedName("name") val name: String?, //ABC News (AU)
        @SerializedName("description") val description: String?, //Australia's most trusted source of local, national and world news. Comprehensive, independent, in-depth analysis, the latest business, sport, weather and more.
        @SerializedName("url") val url: String?, //http://www.abc.net.au/news
        @SerializedName("category") val category: String?, //general
        @SerializedName("language") val language: String?, //en
        @SerializedName("country") val country: String?, //au
        @SerializedName("sortBysAvailable") val sortBysAvailable: List<String?>?
)