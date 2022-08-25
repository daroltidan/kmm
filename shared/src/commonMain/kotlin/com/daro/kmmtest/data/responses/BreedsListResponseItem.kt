package com.daro.kmmtest.data.responses


import kotlinx.serialization.Serializable

@Serializable
data class BreedsListResponseItem(
    val bredFor: String? = null,
    val breedGroup: String? = null,
    val height: Height? = null,
    val id: Int? = null,
    val image: Image? = null,
    val lifeSpan: String? = null,
    val name: String? = null,
    val origin: String? = null,
    val referenceImageId: String? = null,
    val temperament: String? = null,
    val weight: Weight? = null
)
