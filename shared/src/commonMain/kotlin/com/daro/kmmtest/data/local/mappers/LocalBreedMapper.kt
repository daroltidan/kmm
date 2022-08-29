package com.daro.kmmtest.data.local.mappers

import com.daro.kmmtest.Breed as DbBreed
import com.daro.kmmtest.domain.BreedDTO as DomainDTOBreed

class LocalBreedMapper {

    operator fun invoke(breed: DbBreed) =
        DomainDTOBreed(
            id = breed.id,
            name = breed.name,
            image = breed.image
        )
}
