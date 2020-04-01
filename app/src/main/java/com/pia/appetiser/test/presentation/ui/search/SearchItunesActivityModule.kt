package com.pia.appetiser.test.presentation.ui.search

import androidx.appcompat.app.AppCompatActivity
import com.pia.appetiser.test.presentation.common.viewholder.BindingViewHolder
import com.pia.appetiser.test.presentation.common.viewholder.SearchItunesMediaItemViewHolder
import com.pia.appetiser.test.presentation.common.viewholder.ViewHolderFactory
import com.pia.appetiser.test.presentation.di.PerActivity
import com.pia.appetiser.test.presentation.di.modules.BaseActivityModule
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
class SearchItunesActivityModule {


    @Provides
    fun provideSearchItemMovieViewHolderFactory(activity: SearchItunesActivity): ViewHolderFactory<BindingViewHolder<DisplayableItunesDetails>> =
        SearchItunesMediaItemViewHolder.Factory(activity)

    @PerActivity
    @Provides
    fun provideAppCompatActivity(activity: SearchItunesActivity): AppCompatActivity = activity
}