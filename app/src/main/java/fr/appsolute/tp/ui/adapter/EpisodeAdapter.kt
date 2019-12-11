package fr.appsolute.tp.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import fr.appsolute.tp.data.model.Character
import fr.appsolute.tp.data.model.Episode
import fr.appsolute.tp.ui.viewmodel.EpisodeViewModel
import fr.appsolute.tp.ui.widget.holder.CharacterViewHolder
import fr.appsolute.tp.ui.widget.holder.EpisodeViewHolder
import fr.appsolute.tp.ui.widget.holder.OnCharacterClickListener

class EpisodeAdapter() : RecyclerView.Adapter<EpisodeViewHolder>() {


    private var _data = emptyList<Episode>()

    fun submitList(episodeList: List<Episode>){
        _data = episodeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    override fun getItemCount(): Int {
        return _data.size
    }
}