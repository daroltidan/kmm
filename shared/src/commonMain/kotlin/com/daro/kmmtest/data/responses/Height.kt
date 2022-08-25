package com.daro.kmmtest.data.responses


import kotlinx.serialization.Serializable

@Serializable
data class Height(
    val imperial: String?= null,
    val metric: String?= null
)
