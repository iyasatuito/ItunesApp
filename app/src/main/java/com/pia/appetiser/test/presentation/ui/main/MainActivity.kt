package com.pia.appetiser.test.presentation.ui.main

import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.pia.appetiser.test.R
import com.pia.appetiser.test.data.di.GlideApp
import com.pia.appetiser.test.presentation.AppActivity
import com.pia.appetiser.test.presentation.common.adapter.ItunesMediaAdapter
import com.pia.appetiser.test.presentation.common.ext.getViewModel
import com.pia.appetiser.test.presentation.common.ext.observe
import com.pia.appetiser.test.presentation.common.ext.withViewModel
import com.pia.appetiser.test.presentation.common.rx.RxAppBar
import com.pia.appetiser.test.presentation.common.rx.RxRecyclerView
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import com.pia.appetiser.test.presentation.navigation.DetailScreenNavigator
import com.pia.appetiser.test.presentation.ui.search.SearchItunesActivity
import com.pia.appetiser.test.presentation.util.getScreenHeight
import com.pia.appetiser.test.presentation.util.getScreenWidth
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : AppActivity(), ItunesMediaAdapter.Delegate {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var detailScreenNavigator: DetailScreenNavigator
    @Inject
    lateinit var featuredItunesMediaAdapter: ItunesMediaAdapter
    @Inject
    lateinit var tvShowsAdapter: ItunesMediaAdapter
    @Inject
    lateinit var topMusicAdapter: ItunesMediaAdapter


    private val viewModel by lazy { getViewModel<MainActivityViewModel>(viewModelFactory) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
        loadContents()
        observeViewModel()
    }

    private fun loadContents(){
        withViewModel<MainActivityViewModel>(viewModelFactory) {
            refresh()
        }
    }

    private fun observeViewModel(){
        withViewModel<MainActivityViewModel>(viewModelFactory) {
            observe(coverItem, ::displayCoverImage)
            observe(featuredMovieList, ::updateFeaturedMovies)
            observe(tvShowList, ::discoverTVShows)
            observe(topMusicList, ::displayTopMusic)
            observe(isDataUpdated, ::handleLoadingOfDatas)
        }
    }


    private fun handleLoadingOfDatas(result: Result<Boolean>?){
        if(result == null) return
        result.onSuccess {updated ->
            if(updated) {
                viewModel.loadTVShows()
                viewModel.loadFeaturedMovies()
                viewModel.loadTopMusic()
            }
            else toast(R.string.something_went_wrong_error_message).show()
        }

        result.onFailure {
            viewModel.loadTVShows()
            viewModel.loadFeaturedMovies()
            viewModel.loadTopMusic()
            toast(R.string.cached_data_message).show() }
    }


    private fun setView(){
        appBarLayout.layoutParams.height = (getScreenHeight() * .45).toInt()
        appBarLayout.layoutParams.width = getScreenWidth()
        tv_show_recycler.setPadding((getScreenWidth() * .55).toInt(), 0, 0, 0)

        disposableContainer.add(
            RxAppBar.isExpanded(appBarLayout)
            .distinctUntilChanged()
            .subscribe(this::setToolbarIcons))

        disposableContainer.add(RxAppBar.alphaValueChanges(appBarLayout)
            .doOnNext { aFloat -> toolbar_elevation.alpha = (1 - aFloat) }
            .subscribe(tempBg::setAlpha))

        disposableContainer.add(
            RxRecyclerView.onScrollXChanges(tv_show_recycler)
            .doOnNext { _ ->
                tv_shows.alpha = 1f -
                        (0.3f + Math.abs(tv_show_recycler.computeHorizontalScrollOffset() / tv_show_recycler.computeHorizontalScrollRange().toFloat()))
            }
            .doOnNext { _ ->
                tv_show_border.alpha = 1f -
                        (0.3f + Math.abs(tv_show_recycler.computeHorizontalScrollOffset() / tv_show_recycler.computeHorizontalScrollRange().toFloat()))
            }
            .subscribe { dx -> tv_shows.x = tv_shows.x - dx / 6 })

        searchHolder.setOnClickListener{
            startActivity(intentFor<SearchItunesActivity>())
        }


        featured_movies_recycler.isNestedScrollingEnabled = false
        featured_movies_recycler.apply {
            adapter = featuredItunesMediaAdapter
        }

        tv_show_recycler.isNestedScrollingEnabled = false
        tv_show_recycler.apply {
            adapter = tvShowsAdapter
        }

        top_music_recycler.isNestedScrollingEnabled = false
        top_music_recycler.apply {
            adapter = topMusicAdapter
        }
    }

    private fun displayCoverImage(result: Result<DisplayableItunesDetails>?){
        if(result == null) return
        result.onSuccess {
            GlideApp.with(this)
                .load(it.coverImageUrl)
                .into(coverImage)

            now_showing_title.text = it.trackName
        }
    }

    private fun discoverTVShows(result: Result<List<DisplayableItunesDetails>>?){
        if(result == null) return
        result.onSuccess {
            tvShowsAdapter.setItems(it)
        }
    }

    private fun updateFeaturedMovies(result: Result<List<DisplayableItunesDetails>>?){
        if(result == null) return
        result.onSuccess {
            featuredItunesMediaAdapter.setItems(it)
        }
    }

    private fun displayTopMusic(result: Result<List<DisplayableItunesDetails>>?){
        if(result == null) return
        result.onSuccess {
            topMusicAdapter.setItems(it)
        }
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setToolbarIcons(isExpanded: Boolean) {
        window.statusBarColor = if (isExpanded) Color.parseColor("#1F1F1F") else Color.WHITE
        window.decorView.systemUiVisibility = if (isExpanded) 0 else View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        searchHint.setTextColor(if (isExpanded) Color.WHITE else Color.parseColor("#88d8d6d2"))
        searchHolder.background = resources.getDrawable(
            if (isExpanded)
                R.drawable.rectangle_shape_border_white
            else
                R.drawable.rectangle_shape_border_dark
        )
        searchIcon.setImageResource(
            if (isExpanded) R.drawable.ic_search_white_24dp else R.drawable.ic_search_gray
        )
    }

    override fun onItunesItemClicked(itunesResponse: DisplayableItunesDetails) {
        detailScreenNavigator.navigate(itunesResponse)
    }
}
