package com.kakao.son.service

import com.kakao.son.model.KeywordVo
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SearchService(val okHttpClient : OkHttpClient) {
//    @Autowired
//    lateinit var okHttpClient : OkHttpClient

    //curl -v -X GET "https://dapi.kakao.com/v2/search/blog" \
    //--data-urlencode "query=https://brunch.co.kr/@tourism 집짓기" \
    //-H "Authorization: KakaoAK ${REST_API_KEY}"
    fun getSearch(): Any?{
        return okHttpClient.newCall(
                Request.Builder()
                    .url("https://dapi.kakao.com/v2/search/blog?query=집짓기&page=1&size=5")
                    .addHeader("Authorization", "KakaoAK efa774a7cc48088c8c30ecc68645be44")
                    .build()
            ).execute().body?.string()
//            print(response.body?.string())
//            response.body?.string()
    }
}