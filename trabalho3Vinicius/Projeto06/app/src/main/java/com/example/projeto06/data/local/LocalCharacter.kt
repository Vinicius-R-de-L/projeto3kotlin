package com.example.projeto06.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projeto06.data.domain.Character

@Entity
data class LocalCharacter(
    val gender: String?,
    @PrimaryKey
    val id: Int,
    val img_url: String?,
    val name: String?,
    val species: String?
)

fun List<LocalCharacter>.asDomainModel(): List<Character>{

    return map {
        Character(
            id = it.id,
            gender = it.gender,
            img_url = it.img_url,
            name = it.name,
            species = it.species
        )
    }
}