package com.pia.appetiser.test.presentation.common.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pia.appetiser.test.R
import com.pia.appetiser.test.data.di.GlideApp
import com.pia.appetiser.test.presentation.common.adapter.SearchItunesMediaAdapter
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import kotlinx.android.synthetic.main.item_search_itunes.view.*

class SearchItunesMediaItemViewHolder(
    itemView: View,
    private val callback: SearchItunesMediaAdapter.Delegate
) : BindingViewHolder<DisplayableItunesDetails>(itemView) {

    private var itunesDetailsView: DisplayableItunesDetails? = null

    class Factory(private val delegate: SearchItunesMediaAdapter.Delegate) :
        ViewHolderFactory<BindingViewHolder<DisplayableItunesDetails>> {
        override fun create(parent: ViewGroup) = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_search_itunes, parent, false)
            .let { SearchItunesMediaItemViewHolder(it, delegate) }
    }

    init {
        itemView.setOnClickListener {
            itunesDetailsView?.let { callback.onItunesItemClicked(it) }
        }
    }

    override fun bind(item: DisplayableItunesDetails) {
        this.itunesDetailsView = item
        itemView.search_title.text = item.trackName

        GlideApp.with(itemView)
            .load(item.itemImageUrl)
            .into(itemView.search_image)

    }

}