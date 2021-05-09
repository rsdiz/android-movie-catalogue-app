package id.rosyid.moviecatalogue.ui.detail

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.rosyid.moviecatalogue.R
import id.rosyid.moviecatalogue.adapter.CreditsAdapter
import id.rosyid.moviecatalogue.adapter.ListTextAdapter
import id.rosyid.moviecatalogue.adapter.SocialLinkAdapter
import id.rosyid.moviecatalogue.data.BaseEntity
import id.rosyid.moviecatalogue.data.MovieEntity
import id.rosyid.moviecatalogue.data.TvEntity
import id.rosyid.moviecatalogue.databinding.ActivityDetailBinding
import id.rosyid.moviecatalogue.utils.FormatPattern
import id.rosyid.moviecatalogue.utils.toStringWithPattern

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getInt(EXTRA_DATA, -1)
            val type = extras.getString(EXTRA_TYPE, "")
            if (id != -1 && !type.isNullOrEmpty()) {
                title = resources.getString(if (type == TYPE_MOVIES) R.string.detail_movie else R.string.detail_tv_show)
                viewModel.setSelectedId(id, type)
                val data: BaseEntity = viewModel.getItemDetail()
                populateData(data, type)
            }
        }
    }

    private fun populateData(data: BaseEntity, type: String) {
        with(viewBinding) {
            // setup content
            if (type == TYPE_TVSERIES) {
                contentType.text = (data as TvEntity).type
                contentReleaseDate.visibility = View.GONE
            } else if (type == TYPE_MOVIES) {
                contentType.visibility = View.GONE
                labelDuration.layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1.0f
                )
                labelType.visibility = View.GONE
                contentReleaseDate.text = (data as MovieEntity).releaseDate.toStringWithPattern(
                    FormatPattern.DEFAULT_PATTERN
                )
            }
            contentTitle.text = data.title
            Glide.with(baseContext)
                .load(data.poster)
                .into(contentPoster)
            contentPoster.tag = data.poster
            val userScore = data.userScore.toFloat().div(10)
            contentRbUserScore.rating = userScore.div(2)
            contentNumberUserScore.text = userScore.toString()
            contentOverview.text = data.overview
            contentDuration.text = data.runtime
            contentStatus.text = data.status
            contentOriginalLanguage.text = data.originalLanguage

            // setup color content
            val profileBitmap = BitmapFactory.decodeResource(resources, data.poster)
            val palette = Palette.Builder(profileBitmap).generate()
            bannerColor.setBackgroundColor(
                palette.darkVibrantSwatch?.rgb ?: palette.darkMutedSwatch?.rgb
                    ?: ContextCompat.getColor(baseContext, R.color.cod_gray)
            )
            contentTitle.setTextColor(
                palette.lightVibrantSwatch?.rgb ?: palette.lightMutedSwatch?.rgb
                    ?: ContextCompat.getColor(baseContext, R.color.white)
            )
            contentReleaseDate.setTextColor(
                palette.vibrantSwatch?.rgb ?: palette.mutedSwatch?.rgb
                    ?: ContextCompat.getColor(baseContext, R.color.white)
            )

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

            val creditsAdapter = CreditsAdapter()
            creditsAdapter.setList(data.credits)
            rvCredits.apply {
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                setHasFixedSize(true)
                adapter = creditsAdapter
            }

            val keywordAdapter = ListTextAdapter()
            val listKeyword = mutableListOf<String>()
            data.keywords.forEach {
                listKeyword.add(it.name)
            }
            keywordAdapter.setList(listKeyword)
            rvKeywords.apply {
                layoutManager = FlowLayoutManager()
                setHasFixedSize(true)
                adapter = keywordAdapter
            }

            val socialLinkAdapter = SocialLinkAdapter()
            socialLinkAdapter.setList(data.socialLinks)
            rvSocialLink.apply {
                layoutManager =
                    GridLayoutManager(this@DetailActivity, data.socialLinks.size)
                setHasFixedSize(true)
                adapter = socialLinkAdapter
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
        const val TYPE_MOVIES = "type_movies"
        const val TYPE_TVSERIES = "type_tvseries"
    }
}
