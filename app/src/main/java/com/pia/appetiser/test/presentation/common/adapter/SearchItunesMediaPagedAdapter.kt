package com.pia.appetiser.test.presentation.common.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pia.appetiser.test.presentation.common.viewholder.SearchItunesMediaPagedItemViewHolder
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import com.pia.appetiser.test.presentation.model.State

class SearchItunesMediaPagedAdapter(
    private val retry: () -> Unit,
    private val clickListener: (DisplayableItunesDetails) -> Unit
) : PagedListAdapter<DisplayableItunesDetails, RecyclerView.ViewHolder>(DiffCallback) {

    private var state = State.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchItunesMediaPagedItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchItunesMediaPagedItemViewHolder).bind(getItem(position), clickListener)
    }


    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<DisplayableItunesDetails>() {
            override fun areItemsTheSame(oldItem: DisplayableItunesDetails, newItem: DisplayableItunesDetails): Boolean {
                return oldItem.trackId == newItem.trackId
            }

            override fun areContentsTheSame(oldItem: DisplayableItunesDetails, newItem: DisplayableItunesDetails): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}