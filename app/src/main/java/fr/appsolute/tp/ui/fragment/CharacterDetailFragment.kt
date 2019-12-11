package fr.appsolute.tp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import fr.appsolute.tp.R
import fr.appsolute.tp.data.model.Character
import fr.appsolute.tp.ui.adapter.EpisodeAdapter
import fr.appsolute.tp.ui.viewmodel.CharacterViewModel
import fr.appsolute.tp.ui.viewmodel.EpisodeViewModel
import kotlinx.android.synthetic.main.fragment_character_detail.*

class CharacterDetailFragment : Fragment() {

    private var charId: Int = 0
    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var episodeViewModel: EpisodeViewModel
    private lateinit var episodeAdapter: EpisodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            characterViewModel = ViewModelProvider(this, CharacterViewModel).get()
            episodeViewModel = ViewModelProvider(this, EpisodeViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")
        charId = arguments?.getInt(CHARACTER_ID_EXTRA) ?: throw IllegalStateException("No ID Found")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterViewModel.getCharacterDetail(charId) { character -> updateUI(character) }
        episodeAdapter = EpisodeAdapter()
    }

    private fun updateUI(character: Character) {
        Glide.with(this)
            .load(character.image)
            .into(character_image)
        character_gender.text = character.gender
        character_name.text = character.name
        character_species.text = character.species
        character_status.text = character.status
        val episodeIdList = getEpisodeList(character)
        episodeViewModel.getEpisodeFiltered(episodeIdList) {
            episodeAdapter.submitList(it)
            episode_recycler_view.apply {
                adapter = episodeAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun getEpisodeList(character: Character): List<Int> {
        val episodeIdList = mutableListOf<Int>()
        character.episode.map {
            val array = it.split("/")
            episodeIdList.add(array.last().toInt())
        }
        return episodeIdList
    }
}