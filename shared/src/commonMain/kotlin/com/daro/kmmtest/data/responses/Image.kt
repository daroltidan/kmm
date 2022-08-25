package com.daro.kmmtest.data.responses


import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val height: Int?= null,
    val id: String?= null,
    val url: String?= null,
    val width: Int?= null
)
