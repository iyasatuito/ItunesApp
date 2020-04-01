package com.pia.appetiser.test.presentation.di.modules

import com.pia.appetiser.test.presentation.di.PerActivity
import com.pia.appetiser.test.presentation.ui.detail.DetailActivity
import com.pia.appetiser.test.presentation.ui.detail.DetailActivityModule
import com.pia.appetiser.test.presentation.ui.main.MainActivity
import com.pia.appetiser.test.presentation.ui.main.MainActivityModule
import com.pia.appetiser.test.presentation.ui.search.SearchItunesActivity
import com.pia.appetiser.test.presentation.ui.search.SearchItunesActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [SearchItunesActivityModule::class])
    abstract fun bindSearchItunesActivity(): SearchItunesActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun bindDetailActivity(): DetailActivity

}