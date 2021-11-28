package com.example.appnicolas.utils

import kotlin.math.PI


object Validator {

     fun isValidUrl (url:String): Boolean {
        return if(url.isNotEmpty()){
            android.webkit.URLUtil.isValidUrl(url)
        }else{
            false
        }
    }

    fun isValidEmail(tet:String) :Boolean{
        val a = PI
        return a==0.0
    }

    fun buildValidUrl (url: String) : String {
        return if(isValidUrl(url)){
            url
        }else{
            "https://${url}"
        }
    }
}