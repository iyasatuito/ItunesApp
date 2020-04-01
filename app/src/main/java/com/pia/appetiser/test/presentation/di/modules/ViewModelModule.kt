package com.pia.appetiser.test.presentation.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pia.appetiser.test.presentation.di.ViewModelFactory
import com.pia.appetiser.test.presentation.di.ViewModelKey
import com.pia.appetiser.test.presentation.ui.detail.DetailActivityViewModel
import com.pia.appetiser.test.presentation.ui.main.MainActivityViewModel
import com.pia.appetiser.test.presentation.ui.search.SearchItunesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchItunesViewModel::class)
    abstract fun bindSearchMovieViewModel(viewModel: SearchItunesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailActivityViewModel::class)
    abstract fun bindDetailActivityViewModel(viewModel: DetailActivityViewModel): ViewModel


}