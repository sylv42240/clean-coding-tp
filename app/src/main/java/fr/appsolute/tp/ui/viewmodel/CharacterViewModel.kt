package fr.appsolute.tp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.appsolute.tp.data.model.Character
import fr.appsolute.tp.data.repository.CharacterRepository
import kotlinx.coroutines.launch


typealias OnSuccess<T> = (T) -> Unit

class CharacterViewModel private constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    /**
     *  Return the paginated list of character from the API
     */
    val charactersPagedList = repository.getPaginatedList(viewModelScope)


    fun getCharacterDetail(id: Int, onSuccess: OnSuccess<Character>) {
        viewModelScope.launch {
            repository.getCharacterDetail(id)?.run(onSuccess)
        }
    }

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharacterViewModel(CharacterRepository.instance) as T
        }
    }
}