package com.daro.kmmtest.data.remote.responses.mappers

import com.daro.kmmtest.data.remote.responses.BreedsListResponseItem
import com.daro.kmmtest.domain.BreedDTO

class RemoteBreedMapper {

    operator fun invoke(breedsListResponseItem: BreedsListResponseItem) =
        BreedDTO(
            id = breedsListResponseItem.id,
            name = breedsListResponseItem.name ?: "",
            image = breedsListResponseItem.image?.url ?: ""
        )
}
