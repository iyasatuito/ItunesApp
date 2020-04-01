package com.pia.appetiser.test.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import com.pia.appetiser.test.presentation.common.viewholder.BindingViewHolder
import com.pia.appetiser.test.presentation.common.viewholder.ItunesMediaItemViewHolder
import com.pia.appetiser.test.presentation.common.viewholder.ViewHolderFactory
import com.pia.appetiser.test.presentation.di.PerActivity
import com.pia.appetiser.test.presentation.di.modules.BaseActivityModule
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
class MainActivityModule {

    @Provides
    fun provideMovieItemHolderFactory(activity: MainActivity): ViewHolderFactory<BindingViewHolder<DisplayableItunesDetails>> =
        ItunesMediaItemViewHolder.Factory(activity)

    @PerActivity
    @Provides
    fun provideAppCompatActivity(activity: MainActivity): AppCompatActivity = activity
}