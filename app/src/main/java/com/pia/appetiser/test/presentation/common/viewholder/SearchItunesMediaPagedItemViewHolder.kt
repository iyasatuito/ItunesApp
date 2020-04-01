package com.pia.appetiser.test.presentation.common.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pia.appetiser.test.R
import com.pia.appetiser.test.data.di.GlideApp
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import com.pia.appetiser.test.presentation.util.getScreenWidth
import kotlinx.android.synthetic.main.item_search_itunes.view.*
import java.text.DecimalFormat

class SearchItunesMediaPagedItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    init {
        val computedWidth = (itemView.context.getScreenWidth() / 2.8).toInt()
        val computedHeight = (computedWidth + computedWidth / 2.5).toInt()

        val params = itemView.search_image.layoutParams
        val titleParams = itemView.search_title.layoutParams

        params.width = computedWidth
        params.height = computedHeight

        titleParams.width = computedWidth

        itemView.search_title.layoutParams = titleParams

        itemView.gradientOverlay.layoutParams = params
        itemView.search_image.layoutParams = params
    }

    fun bind(itunesDetailsView : DisplayableItunesDetails?, clickListener : (DisplayableItunesDetails) -> Unit) {
        if (itunesDetailsView != null) {
            itemView.search_title.text = itunesDetailsView.trackName
            itemView.price_value.text = itunesDetailsView.trackPriceString
            itemView.genre_value.text = itunesDetailsView.primaryGenreName
            GlideApp.with(itemView)
                .load(itunesDetailsView.itemImageUrl)
                .placeholder(R.drawable.media_placeholder)
                .error(R.drawable.media_placeholder)
                .into(itemView.search_image)

            itemView.setOnClickListener {
                clickListener(itunesDetailsView)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): SearchItunesMediaPagedItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_itunes, parent, false)
            return SearchItunesMediaPagedItemViewHolder(view)
        }
    }
}