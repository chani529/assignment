package com.kakao.son.openApi

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kakao.son.dto.BlogDTO
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class BlogSearchHandler {

    @Autowired
    lateinit var okHttpClient : OkHttpClient

    @Value("\${kakao.api.key}")
    lateinit var kakaoApiKey: String

    private val kakaoBlogSearchURL = "https://dapi.kakao.com/v2/search/blog"


    fun getKakaoBlogSearch(query : String, page : Int, sort : String) : BlogDTO {
        val result = okHttpClient.newCall(
            Request.Builder()
                .url("${kakaoBlogSearchURL}?query=${query}&page=${page}&sort=${sort}")
                .addHeader("Authorization", "KakaoAK $kakaoApiKey")
                .build()
        ).execute().use { it ->
            if(!it.isSuccessful) throw Exception("Kakao API Error")
            it.body?.string()
        }

        return Gson().fromJson(result, BlogDTO::class.java)

    }

}