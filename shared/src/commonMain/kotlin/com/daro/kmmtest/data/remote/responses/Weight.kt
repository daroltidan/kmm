package com.daro.kmmtest.data.remote.responses


import kotlinx.serialization.Serializable

@Serializable
data class Weight(
    val imperial: String? = null,
    val metric: String? = null
)
