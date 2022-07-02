package com.example.projeto06.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.projeto06.data.domain.Character
import com.example.projeto06.data.local.CharacterDao
import com.example.projeto06.data.local.asDomainModel
import com.example.projeto06.data.source.FinalSpaceApi
import com.example.projeto06.data.source.SourceCharacterContainer
import com.example.projeto06.data.source.asLocalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepositoy(private val characterDao: CharacterDao) {

    val characters: LiveData<List<Character>> = Transformations.map(
        characterDao.getAllChars()
    ){
        it.asDomainModel()
    }

    suspend fun refreshCharacters(){
        withContext(Dispatchers.IO){
            val characters = FinalSpaceApi.retrofitService.getChars()
            val charactersContainer = SourceCharacterContainer(characters)
            characterDao.insertAllChars(charactersContainer.asLocalModel())
        }
    }


}