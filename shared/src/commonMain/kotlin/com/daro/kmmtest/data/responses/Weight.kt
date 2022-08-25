package com.daro.kmmtest.data.responses


import kotlinx.serialization.Serializable

@Serializable
data class Weight(
    val imperial: String? = null,
    val metric: String? = null
)
