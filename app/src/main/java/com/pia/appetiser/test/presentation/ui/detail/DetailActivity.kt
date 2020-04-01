package com.pia.appetiser.test.presentation.ui.detail

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.VideoView
import androidx.lifecycle.ViewModelProvider
import com.pia.appetiser.test.R
import com.pia.appetiser.test.data.di.GlideApp
import com.pia.appetiser.test.presentation.AppActivity
import com.pia.appetiser.test.presentation.common.adapter.ItunesMediaAdapter
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import com.pia.appetiser.test.presentation.navigation.DetailScreenNavigator
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast
import javax.inject.Inject


class DetailActivity : AppActivity(), ItunesMediaAdapter.Delegate {
    override fun onItunesItemClicked(itunesResponse: DisplayableItunesDetails) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val KEY_ITUNES_OBJECT = "ITUNES_DETAILS_PARCELABLE"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var detailScreenNavigator: DetailScreenNavigator


    private val itunesDetail by lazy {
        (intent.getParcelableExtra(KEY_ITUNES_OBJECT) as DisplayableItunesDetails)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setView()

    }

    private fun setView() {

        GlideApp.with(this)
            .load(itunesDetail.coverImageUrl)
            .into(itunes_backdrop)

        itunes_poster_placeholder.visibility = View.GONE
        val video = findViewById<VideoView>(R.id.itunes_poster_detail) as VideoView
        video.setVideoPath(itunesDetail.previewUrl)
        video.setOnErrorListener { mp, what, extra ->
            toast(R.string.cannot_play_video_error_message)
            GlideApp.with(this)
                .load(R.drawable.media_placeholder)
                .into(itunes_poster_placeholder)
            itunes_poster_placeholder.visibility = View.VISIBLE
             false
        }
        video.start()

        if (itunesDetail.kind.equals("song") || itunesDetail.kind.equals("podcast")) itunes_poster_placeholder.visibility = View.VISIBLE
        if (itunesDetail.contentAdvisoryRating != null) itunes_rating.text = resources.getString(R.string.track_rating, itunesDetail.contentAdvisoryRating) else itunes_rating.visibility = View.GONE

        itunes_title.text = itunesDetail.trackName
        itunes_artist_name_value.text = itunesDetail.artistName
        itunes_artist_name_small_value.text = itunesDetail.artistName
        itunes_release_date_value.text = itunesDetail.releaseDate
        itunes_track_name_small.text = itunesDetail.trackName
        itunes_genre_values.text = itunesDetail.primaryGenreName
        itunes_budget_values.text = itunesDetail.trackPriceString
        if (itunesDetail.longDescription != null) {
            itunes_description_values.text = itunesDetail.longDescription
        } else if (itunesDetail.shortDescription != null) {
            itunes_description_values.text = itunesDetail.shortDescription
        } else {
            itunes_description_values.text = resources.getString(R.string.no_data_text)
        }

    }

}
