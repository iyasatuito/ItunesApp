package com.pia.appetiser.test.data.di

import com.pia.appetiser.test.data.source.repository.ItunesRepository
import com.pia.appetiser.test.data.source.repository.ItunesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindItunesRepository(itunesRepositoryImpl: ItunesRepositoryImpl): ItunesRepository

}