package com.example.projeto06.views

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.projeto06.data.repository.CharacterRepositoy
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.IllegalArgumentException


class CharactersViewModel(private val repositoy: CharacterRepositoy) : ViewModel() {

    init {
        if(repositoy.characters.value.isNullOrEmpty())
            refreshDataFromRepository()
    }

    val characters = repositoy.characters

    private fun refreshDataFromRepository(){
        viewModelScope.launch {
            try {
                repositoy.refreshCharacters()
            }catch (networkError: IOException){
                Log.d("Error", "${networkError.message}")
            }
        }
    }

}

class CharacterVMFactory(private val repositoy: CharacterRepositoy) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CharactersViewModel::class.java))
            return CharactersViewModel(repositoy) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}