package com.pia.appetiser.test.presentation.common.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BindingViewHolder<Item>(itemView: View) : ViewHolder(itemView) {
  abstract fun bind(item: Item)
}