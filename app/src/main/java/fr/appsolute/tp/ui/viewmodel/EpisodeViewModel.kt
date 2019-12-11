package fr.appsolute.tp.ui.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.appsolute.tp.RickAndMortyApplication
import fr.appsolute.tp.data.model.Episode
import fr.appsolute.tp.data.repository.CharacterRepository
import fr.appsolute.tp.data.repository.EpisodeRepository
import kotlinx.coroutines.launch


class EpisodeViewModel private constructor(
    private val repository: EpisodeRepository
): ViewModel() {

    /**
     *  Return the paginated list of character from the API
     */
    fun getAllEpisode(onSuccess: OnSuccess<List<Episode>>) {
        viewModelScope.launch {
            repository.getAllEpisode()?.run(onSuccess)
        }
    }

    fun getEpisodeById(id: Int, onSuccess: OnSuccess<Episode>){
        viewModelScope.launch {
            repository.getEpisodeById(id)?.run(onSuccess)
        }
    }

    fun getEpisodeFiltered(idList: List<Int>, block : OnSuccess<List<Episode>>) {
        viewModelScope.launch {
            repository.getFilteredEpisode(idList).run(block)
        }
    }


    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return EpisodeViewModel(EpisodeRepository.newInstance()) as T
        }
    }
}