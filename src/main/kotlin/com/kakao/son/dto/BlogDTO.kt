package com.kakao.son.dto

data class BlogDTO (
    val documents : List<Documents?>,
    val meta : Meta,
)

data class Documents (
    val blogname : String,
    val contents : String,
    val datetime : String,
    val thumbnail : String,
    val title : String,
    val url : String
)

data class Meta (
    val is_end : Boolean,
    val pageable_count : Int,
    val total_count : Int
)