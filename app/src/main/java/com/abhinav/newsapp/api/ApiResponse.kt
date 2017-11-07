package com.abhinav.newsapp.api

import android.support.v4.util.ArrayMap
import android.util.Log
import retrofit2.Response
import java.io.IOException
import java.util.regex.Pattern

/**
 * Created by abhinav.sharma on 06/11/17.
 */
class ApiResponse<T> {
    val code: Int
    val body: T?
    val errorMessage: String?
    val links: MutableMap<String, String>

    constructor(error: Throwable) {
        code = 500
        body = null
        errorMessage = error.message
        links = ArrayMap<String, String>()
    }

    companion object {
        private val LINK_PATTERN = Pattern
                .compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"")
        private val PAGE_PATTERN = Pattern.compile("page=(\\d)+")
        private val NEXT_LINK = "next"
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody()!!.string()
                } catch (ignored: IOException) {
                    Log.e("Parse", "error while parsing response")
                }

            }
            if (message == null || message.trim { it <= ' ' }.isEmpty()) {
                message = response.message()
            }
            errorMessage = message
            body = null
        }
        val linkHeader = response.headers().get("link")
        if (linkHeader == null) {
            links = ArrayMap<String, String>()
        } else {
            links = ArrayMap<String, String>()
            val matcher = LINK_PATTERN.matcher(linkHeader)

            while (matcher.find()) {
                val count = matcher.groupCount()
                if (count == 2) {
                    links.put(matcher.group(2), matcher.group(1))
                }
            }
        }
    }

    val isSuccessful: Boolean
        get() = code >= 200 && code < 300

    val nextPage: Int?
        get() {
            val next = links[NEXT_LINK] ?: return null
            val matcher = PAGE_PATTERN.matcher(next)
            if (!matcher.find() || matcher.groupCount() != 1) {
                return null
            }
            try {
                return Integer.parseInt(matcher.group(1))
            } catch (ex: NumberFormatException) {
                Log.e("Parse", "cannot parse next page from " + next)
                return null
            }

        }
}