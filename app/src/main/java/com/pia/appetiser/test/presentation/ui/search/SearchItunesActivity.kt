package com.pia.appetiser.test.presentation.ui.search

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pia.appetiser.test.R
import com.pia.appetiser.test.presentation.AppActivity
import com.pia.appetiser.test.presentation.common.adapter.SearchItunesMediaAdapter
import com.pia.appetiser.test.presentation.common.adapter.SearchItunesMediaPagedAdapter
import com.pia.appetiser.test.presentation.common.ext.withViewModel
import com.pia.appetiser.test.presentation.common.rx.RxSearchViewSupport
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import com.pia.appetiser.test.presentation.model.State
import com.pia.appetiser.test.presentation.navigation.DetailScreenNavigator
import com.pia.appetiser.test.presentation.ui.common.ItemDecorationAlbumColumns
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_search_itunes.*
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchItunesActivity : AppActivity(), SearchItunesMediaAdapter.Delegate {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var detailScreenNavigator: DetailScreenNavigator
    lateinit var searchItunesMediaPagedAdapter: SearchItunesMediaPagedAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_itunes)
        setupSearchView()
        setupSearchList()

        val mToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(mToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = null

        withViewModel<SearchItunesViewModel>(viewModelFactory) {
            listing.observe(this@SearchItunesActivity, Observer {
                searchItunesMediaPagedAdapter.submitList(it)
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupSearchList() {
        searchItunesMediaPagedAdapter = SearchItunesMediaPagedAdapter({
            withViewModel<SearchItunesViewModel>(viewModelFactory) {
                retry()
            }
        }) {
            //to edit
            detailScreenNavigator.navigate(it)
        }

        searchItunesMediaPagedAdapter.setState(State.DONE)


        val itemCategoryDecorationAlbumColumns =
            ItemDecorationAlbumColumns(resources.getDimensionPixelSize(R.dimen.dp_7), 2)

        itunes_search_recyclerview.isNestedScrollingEnabled = true
        itunes_search_recyclerview.apply {
            adapter = searchItunesMediaPagedAdapter
            addItemDecoration(itemCategoryDecorationAlbumColumns)
        }
    }


    override fun onItunesItemClicked(itunesResponse: DisplayableItunesDetails) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setupSearchView() {
        itunes_search_view.onActionViewExpanded()
        itunes_search_view.maxWidth = Integer.MAX_VALUE
        itunes_search_view.setBackgroundColor(Color.TRANSPARENT)

        disposableContainer.add(
            RxSearchViewSupport.changes(itunes_search_view).throttleFirst(
                500,
                TimeUnit.MILLISECONDS,
                AndroidSchedulers.mainThread())
                .filter { searchQuery -> searchQuery.isNullOrBlank() || searchQuery.length >= 3 }
                .subscribe({ term ->
                    withViewModel<SearchItunesViewModel>(viewModelFactory) {
                        searchMovieWithPage(term = term, country = "au", media = "movie")
                        listing.observe(this@SearchItunesActivity, Observer {
                            searchItunesMediaPagedAdapter.submitList(it)

                        })
                    }
                },
                    {
                        toast(R.string.an_error_occurred_message)
                    })
        )

        val closeImageView = itunes_search_view.findViewById(R.id.search_close_btn) as ImageView
        if (closeImageView != null) {
            closeImageView.setImageDrawable(resources.getDrawable(R.drawable.transparentbg))
            closeImageView.isEnabled = false
        }

        val searchTextView = itunes_search_view.findViewById(R.id.search_src_text) as EditText
        if (searchTextView != null) {

            val params = searchTextView.layoutParams as LinearLayout.LayoutParams
            params.topMargin = 0
            params.bottomMargin = 0
            searchTextView.layoutParams = params
            searchTextView.setHintTextColor(resources.getColor(R.color.view_divider_thin_color))
            searchTextView.hint = resources.getString(R.string.search_text)
            searchTextView.setTextColor(resources.getColor(R.color.primary_text_color))

            val search_text =
                itunes_search_view.findViewById(R.id.search_src_text) as AutoCompleteTextView

            search_text.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimensionPixelSize(R.dimen.sp_26).toFloat()
            )

            try {
                val mCursorDrawableRes = TextView::class.java.getDeclaredField("mCursorDrawableRes")
                mCursorDrawableRes.isAccessible = true
                mCursorDrawableRes.set(searchTextView, R.drawable.search_cursor)
            } catch (e: Exception) {
            }

        }
    }
}