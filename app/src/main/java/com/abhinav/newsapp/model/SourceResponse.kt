package com.abhinav.newsapp.ui.model

import com.google.gson.annotations.SerializedName

/**
 * Created by abhinav.sharma on 31/10/17.
 */

data class SourceResponse(
        @SerializedName("status") var status: String?, //ok
        @SerializedName("sources") var sources: List<Source>
)

data class Source(
        @SerializedName("id") var id: String?, //abc-news-au
        @SerializedName("name") var name: String?, //ABC News (AU)
        @SerializedName("description") var description: String?, //Australia's most trusted source of local, national and world news. Comprehensive, independent, in-depth analysis, the latest business, sport, weather and more.
        @SerializedName("url") var url: String?, //http://www.abc.net.au/news
        @SerializedName("category") var category: String?, //general
        @SerializedName("language") var language: String?, //en
        @SerializedName("country") var country: String? //au
)