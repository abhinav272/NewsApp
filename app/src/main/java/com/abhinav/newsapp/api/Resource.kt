package com.abhinav.newsapp.api

/**
 * Created by abhinav.sharma on 06/11/17.
 */
class Resource<T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)
        fun <T> error(data: T?, message: String): Resource<T> = Resource(Status.ERROR, data, message)
        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data, null)
    }

    override fun toString(): String {
        return "Resource(status=$status, data=$data, message=$message)"
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 27 * result + (data?.hashCode() ?: 0)
        result = 27 * result + (message?.hashCode() ?: 0)
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Resource<*>

        if (status != other.status) return false
        if (data != other.data) return false
        if (message != other.message) return false

        return true
    }

}