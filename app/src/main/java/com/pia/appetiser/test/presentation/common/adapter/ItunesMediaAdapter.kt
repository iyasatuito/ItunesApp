package com.pia.appetiser.test.presentation.common.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pia.appetiser.test.presentation.common.viewholder.BindingViewHolder
import com.pia.appetiser.test.presentation.common.viewholder.ViewHolderFactory
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import javax.inject.Inject

class ItunesMediaAdapter @Inject constructor(
    private val viewHolderFactory: ViewHolderFactory<BindingViewHolder<DisplayableItunesDetails>>
) : RecyclerView.Adapter<BindingViewHolder<DisplayableItunesDetails>>() {

    interface Delegate {
        fun onItunesItemClicked(itunesResponse: DisplayableItunesDetails)
    }

    private val items = mutableListOf<DisplayableItunesDetails>()

    fun setItems(newItems: List<DisplayableItunesDetails>) {
        val result = DiffUtil.calculateDiff(
            DiffCallback(items, newItems)
        )

        items.clear()
        items.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = viewHolderFactory.create(parent)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BindingViewHolder<DisplayableItunesDetails>, position: Int) =
        holder.bind(items[position])


    class DiffCallback(
        private val oldList: List<DisplayableItunesDetails>,
        private val newList: List<DisplayableItunesDetails>
    ) :
        DiffUtil.Callback() {

        override fun areItemsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ) = oldList[oldItemPosition].trackId == newList[newItemPosition].trackId

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areContentsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ) = oldList[oldItemPosition] == newList[newItemPosition]

    }

}