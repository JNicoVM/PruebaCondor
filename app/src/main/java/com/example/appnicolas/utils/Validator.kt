package com.example.appnicolas.utils

import android.webkit.URLUtil

object Validator {

     fun isValidUrl (url:String): Boolean {
        return if(url.isNotEmpty()){
            URLUtil.isValidUrl(url)
        }else{
            false
        }
    }

    fun buildValidUrl (url: String) : String {
        return if(isValidUrl(url)){
            url
        }else{
            "https://${url}"
        }
    }
}