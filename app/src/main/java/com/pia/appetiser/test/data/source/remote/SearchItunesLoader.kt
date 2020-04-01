package com.pia.appetiser.test.data.source.remote

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.pia.appetiser.test.domain.appusecase.SearchItunesUseCase
import com.pia.appetiser.test.domain.params.SearchItunesParam
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import com.pia.appetiser.test.presentation.model.State
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers


class SearchItunesLoader(
    private val searchItunesUseCase: SearchItunesUseCase,
    private val term : String,
    private val country : String,
    private val media : String
    ) : PageKeyedDataSource<Int, DisplayableItunesDetails>() {

    val state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, DisplayableItunesDetails>) {
        searchItunesUseCase
           .getObservable(SearchItunesParam(term,country, media))
           .subscribe(
               {
                   updateState(State.DONE)
                   callback.onResult(it, null, null)
               },
               {
                   updateState(State.ERROR)
                   setRetry(Action { loadInitial(params, callback) })
               }
           )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DisplayableItunesDetails>) {
        updateState(State.LOADING)
        searchItunesUseCase
            .getObservable(SearchItunesParam(term,country, media))
            .subscribe(
                {
                    updateState(State.DONE)
                    callback.onResult(it,params.key + 1)
                },
                {
                    updateState(State.ERROR)
                    setRetry(Action { loadAfter(params, callback) })
                }
            )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DisplayableItunesDetails>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }


    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

    class Factory(
        private val searchItunesUseCase: SearchItunesUseCase,
        private val term : String,
        private val country : String,
        private val media : String
    ) : DataSource.Factory<Int, DisplayableItunesDetails>() {

        val sourceLiveData = MutableLiveData<SearchItunesLoader>()

        override fun create(): DataSource<Int, DisplayableItunesDetails> {

            val dataSource = SearchItunesLoader(
                searchItunesUseCase,
                term, country, media
            )
            sourceLiveData.postValue(dataSource)
            return dataSource
        }

    }

}