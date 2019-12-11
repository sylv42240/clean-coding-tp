package fr.appsolute.tp.ui.adapter

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.appsolute.tp.data.model.Episode
import fr.appsolute.tp.ui.widget.holder.EpisodeViewHolder
import kotlin.math.roundToInt

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

    class ItemDecoration : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            parent.run {
                outRect.set(
                    dp(16),
                    dp(8),
                    dp(16),
                    dp(8)
                )
            }
        }
    }
}

fun View.dp(number: Number): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        number.toFloat(),
        resources.displayMetrics
    ).roundToInt()
}

fun View.dpF(number: Number): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        number.toFloat(),
        resources.displayMetrics
    )
}