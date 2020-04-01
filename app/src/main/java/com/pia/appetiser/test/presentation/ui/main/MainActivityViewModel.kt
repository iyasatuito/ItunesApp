package com.pia.appetiser.test.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pia.appetiser.test.domain.appusecase.*
import com.pia.appetiser.test.presentation.common.arch.BaseViewModel
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import javax.inject.Inject
import kotlin.random.Random

/**
 *
 * @property refresh initially retrieves data from remote source
 * @property loadFeaturedMovies loads successfully stored movies data from remote
 * @property loadTopMusic loads successfully stored music data from remote
 * @property loadTVShows loads successfully stored tv shows data from remote
 */

class MainActivityViewModel @Inject constructor(
    private val loadTVShowsUseCase: LoadTVShowsUseCase,
    private val loadFeaturedMoviesUseCase: LoadFeaturedMoviesUseCase,
    private val loadTopMusicUseCase: LoadTopMusicUseCase,
    private val updateTopMusicUseCase: UpdateTopMusicUseCase,
    private val updateTVShowsUseCase: UpdateTVShowsUseCase,
    private val updateFeaturedMoviesUseCase: UpdateFeaturedMoviesUseCase
) : BaseViewModel() {


    val coverItem: LiveData<Result<DisplayableItunesDetails>> get() = coverItemResultLiveData
    private val coverItemResultLiveData = MutableLiveData<Result<DisplayableItunesDetails>>()

    val featuredMovieList: LiveData<Result<List<DisplayableItunesDetails>>> get() = featuredMovieListResultLiveData
    private val featuredMovieListResultLiveData = MutableLiveData<Result<List<DisplayableItunesDetails>>>()

    val tvShowList: LiveData<Result<List<DisplayableItunesDetails>>> get() = tvShowListResultLiveData
    private val tvShowListResultLiveData = MutableLiveData<Result<List<DisplayableItunesDetails>>>()

    val topMusicList: LiveData<Result<List<DisplayableItunesDetails>>> get() = topMusicListResultLiveData
    private val topMusicListResultLiveData = MutableLiveData<Result<List<DisplayableItunesDetails>>>()

    private val IsDataLoadedLiveData = MutableLiveData<Result<Boolean>>()
    val isDataUpdated: LiveData<Result<Boolean>> = IsDataLoadedLiveData


    fun refresh(){
        updateTVShowsUseCase.getObservable()
            .andThen(updateFeaturedMoviesUseCase.getObservable())
            .andThen(updateTopMusicUseCase.getObservable())
            .subscribe(
                {IsDataLoadedLiveData.postValue(Result.success(true))},
                {IsDataLoadedLiveData.postValue(Result.failure(it))}
            ).run(::addToDisposables)

    }

    fun loadFeaturedMovies(){
        loadFeaturedMoviesUseCase
            .getObservable()
            .subscribe(
                {
                    featuredMovieListResultLiveData.postValue(Result.success(it))
                    coverItemResultLiveData.postValue(Result.success(it[Random.nextInt(0, it.size)]))
                },
                {
                    featuredMovieListResultLiveData.postValue(Result.failure(Throwable("Error")))
                    coverItemResultLiveData.postValue(Result.failure(Throwable("Error")))
                }
            ).run(::addToDisposables)
    }

    fun loadTopMusic(){
        loadTopMusicUseCase
            .getObservable()
            .subscribe(
                {
                    topMusicListResultLiveData.postValue(Result.success(it))
                },
                {
                    topMusicListResultLiveData.postValue(Result.failure(Throwable("Error")))
                }
            ).run(::addToDisposables)
    }


    fun loadTVShows(){
        loadTVShowsUseCase
            .getObservable()
            .subscribe(
                {
                    tvShowListResultLiveData.postValue(Result.success(it))
                },
                {
                    tvShowListResultLiveData.postValue(Result.failure(Throwable("Error")))
                }
            ).run(::addToDisposables)
    }


}