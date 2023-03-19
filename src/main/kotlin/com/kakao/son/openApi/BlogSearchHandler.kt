package com.kakao.son.openApi

import com.google.gson.Gson
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

    fun getKakaoBlogSearch(query : String, page : Int, sort : String) : BlogDTO {
        val result = okHttpClient.newCall(
            Request.Builder()
                .url("https://dapi.kakao.com/v2/search/blog?query=${query}&page=${page}&sort=${sort}")
                .addHeader("Authorization", "KakaoAK $kakaoApiKey")
                .build()
        ).execute().body?.string()

        return Gson().fromJson(result, BlogDTO::class.java)
    }

}