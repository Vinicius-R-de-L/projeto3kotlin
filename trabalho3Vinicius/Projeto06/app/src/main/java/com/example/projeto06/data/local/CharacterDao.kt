package com.example.projeto06.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.projeto06.data.domain.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM localcharacter")
    fun getAllChars(): LiveData<List<LocalCharacter>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllChars(character: List<LocalCharacter>)
}