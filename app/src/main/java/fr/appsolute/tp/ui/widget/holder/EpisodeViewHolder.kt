package fr.appsolute.tp.ui.widget.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.appsolute.tp.R
import fr.appsolute.tp.data.model.Episode
import kotlinx.android.synthetic.main.holder_episode.view.*


class EpisodeViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: Episode) {
        itemView.apply {
            this.holder_episode_full_name.text = model.name
            this.holder_episode_date.text = model.airDate
            this.holder_episode_season.text = model.episode
        }
    }

    companion object {

        fun create(parent: ViewGroup): EpisodeViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.holder_episode,
                parent,
                false
            )
            return EpisodeViewHolder(view)
        }
    }

}