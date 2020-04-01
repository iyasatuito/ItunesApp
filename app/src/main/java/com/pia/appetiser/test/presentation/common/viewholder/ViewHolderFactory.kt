package com.pia.appetiser.test.presentation.common.viewholder


import android.view.ViewGroup

interface ViewHolderFactory<ViewHolder> {
  fun create(parent: ViewGroup): ViewHolder
}