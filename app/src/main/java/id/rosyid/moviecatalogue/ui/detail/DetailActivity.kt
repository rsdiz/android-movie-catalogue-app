package id.rosyid.moviecatalogue.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.rosyid.moviecatalogue.R
import id.rosyid.moviecatalogue.adapter.ListTextAdapter
import id.rosyid.moviecatalogue.data.remote.api.TMdbService
import id.rosyid.moviecatalogue.databinding.ActivityDetailBinding
import id.rosyid.moviecatalogue.utils.FormatPattern.DEFAULT_PATTERN
import id.rosyid.moviecatalogue.utils.ImageConfiguration
import id.rosyid.moviecatalogue.utils.Resource
import id.rosyid.moviecatalogue.utils.toStringWithPattern
import java.time.LocalDate

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getInt(EXTRA_DATA, -1)
            val type = extras.getString(EXTRA_TYPE, "")
            if (id != -1 && !type.isNullOrEmpty()) {
                title =
                    resources.getString(if (type == TYPE_MOVIES) R.string.detail_movie else R.string.detail_tv_show)
                viewModel.initDetail(id, type)
                setData(type)
            }
        }
    }

    private fun setData(type: String) {
        with(viewBinding) {
            showLoading()
            var data = DetailModel()
            // setup content
            if (type == TYPE_TVSERIES) {
                viewModel.tvDetail.observe(this@DetailActivity) { resource ->
                    if (resource != null)
                        when (resource.status) {
                            Resource.Status.LOADING -> {
                                showLoading()
                            }
                            Resource.Status.SUCCESS -> {
                                resource.data?.let {
                                    data = DetailModel(
                                        title = it.name,
                                        backdropPath = it.backdropPath,
                                        posterPath = it.posterPath,
                                        date = LocalDate.parse(it.firstAirDate)
                                            .toStringWithPattern(DEFAULT_PATTERN),
                                        overview = it.overview,
                                        voteAverage = it.voteAverage.toFloat(),
                                        voteCount = it.voteCount,
                                        tagline = it.tagline,
                                        genres = it.genres,
                                        homepage = it.homepage
                                    )
                                }
                                populateData(data)
                                showContent()
                            }
                            Resource.Status.ERROR -> {
                                showMessage(resource.message)
                            }
                        }
                }
                contentReleaseDate.visibility = View.GONE
            } else if (type == TYPE_MOVIES) {
                viewModel.movieDetail.observe(this@DetailActivity) { resource ->
                    if (resource != null)
                        when (resource.status) {
                            Resource.Status.LOADING -> {
                                showLoading()
                            }
                            Resource.Status.SUCCESS -> {
                                resource.data?.let {
                                    data = DetailModel(
                                        title = it.title,
                                        backdropPath = it.backdropPath,
                                        posterPath = it.posterPath,
                                        date = LocalDate.parse(it.releaseDate)
                                            .toStringWithPattern(DEFAULT_PATTERN),
                                        overview = it.overview,
                                        voteAverage = it.voteAverage.toFloat(),
                                        voteCount = it.voteCount,
                                        tagline = it.tagline,
                                        genres = it.genres,
                                        homepage = it.homepage
                                    )
                                }
                                populateData(data)
                                showContent()
                            }
                            Resource.Status.ERROR -> {
                                showMessage(resource.message)
                            }
                        }
                }
            }
        }
    }

    private fun populateData(data: DetailModel) {
        with(viewBinding) {
            contentTitle.text = data.title
            Glide.with(baseContext)
                .load(
                    Uri.parse(
                        StringBuilder(TMdbService.IMAGE_BASE_URL).append(ImageConfiguration.POSTER_SIZE[4])
                            .append(data.posterPath).toString()
                    )
                )
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(contentPoster)
            Glide.with(baseContext)
                .load(
                    Uri.parse(
                        StringBuilder(TMdbService.IMAGE_BASE_URL).append(ImageConfiguration.BACKDROP_SIZE[1])
                            .append(data.backdropPath).toString()
                    )
                )
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .centerCrop()
                .into(bannerImage)
            contentPoster.tag = data.posterPath
            contentRbUserScore.rating = data.voteAverage.div(2)
            contentNumberUserScore.text = data.voteAverage.toString()
            contentOverview.text = data.overview
            contentTagline.text = data.tagline
            contentReleaseDate.text = data.date
            // setup recycler view
            val genreAdapter = ListTextAdapter()
            val listGenre = mutableListOf<String>()
            data.genres.forEach {
                listGenre.add(it.name)
            }
            genreAdapter.setList(listGenre)
            rvGenre.apply {
                layoutManager = FlowLayoutManager()
                setHasFixedSize(true)
                adapter = genreAdapter
            }
            // setup button homepage
            buttonHomepage.setOnClickListener {
                val openHomepage = Intent(Intent.ACTION_VIEW)
                openHomepage.data = Uri.parse(data.homepage)
                startActivity(openHomepage)
            }
        }
    }

    private fun showLoading() {
        with(viewBinding) {
            groupContent.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            message.visibility = View.GONE
        }
    }

    private fun showMessage(data: String?) {
        with(viewBinding) {
            groupContent.visibility = View.GONE
            progressBar.visibility = View.GONE
            message.visibility = View.VISIBLE
            message.text = data
        }
    }

    private fun showContent() {
        with(viewBinding) {
            progressBar.visibility = View.GONE
            groupContent.visibility = View.VISIBLE
            message.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
        const val TYPE_MOVIES = "type_movies"
        const val TYPE_TVSERIES = "type_tvseries"
    }
}
