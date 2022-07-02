package com.example.projeto06.data.source

import com.example.projeto06.data.domain.Character
import com.example.projeto06.data.local.LocalCharacter

data class SourceCharacterContainer(
    val sourceCharacter: List<SourceCharacter>
)

data class SourceCharacter(
    val gender: String?,
    val id: Int,
    val img_url: String?,
    val name: String?,
    val species: String?
)

fun SourceCharacterContainer.asDomainModel(): List<Character>{

    return sourceCharacter.map {
        Character(
            id = it.id,
            gender = it.gender,
            img_url = it.img_url,
            name = it.name,
            species = it.species
        )
    }
}

fun SourceCharacterContainer.asLocalModel(): List<LocalCharacter>{

    return sourceCharacter.map {
        LocalCharacter(
            id = it.id,
            gender = it.gender,
            img_url = it.img_url,
            name = it.name,
            species = it.species
        )
    }
}