package com.example.projeto06

import android.app.Application
import com.example.projeto06.data.local.CharactersDatabase
import com.example.projeto06.data.repository.CharacterRepositoy

class FinalSpaceApplication:Application() {

    private val database: CharactersDatabase by lazy{
        CharactersDatabase.getInstance(this)

    }

    val repositoy: CharacterRepositoy by lazy{
        CharacterRepositoy(database.characterDao())
    }
}