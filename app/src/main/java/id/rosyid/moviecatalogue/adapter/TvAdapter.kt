package id.rosyid.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.rosyid.moviecatalogue.R
import id.rosyid.moviecatalogue.data.entities.Tv
import id.rosyid.moviecatalogue.data.remote.api.TMdbService
import id.rosyid.moviecatalogue.databinding.ItemsMoviesBinding
import id.rosyid.moviecatalogue.ui.homepage.ItemsCallback
import id.rosyid.moviecatalogue.utils.FormatPattern.DEFAULT_PATTERN
import id.rosyid.moviecatalogue.utils.ImageConfiguration
import id.rosyid.moviecatalogue.utils.toStringWithPattern
import java.time.LocalDate

class TvAdapter(private val callback: ItemsCallback) :
    RecyclerView.Adapter<TvAdapter.ViewHolder>() {
    private var listTvSeries = mutableListOf<Tv>()

    fun setTvSeries(tvSeries: List<Tv>?) {
        if (tvSeries == null) return
        listTvSeries.clear()
        listTvSeries.addAll(tvSeries)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsMoviesBinding =
            ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listTvSeries[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listTvSeries.size

    inner class ViewHolder(private val binding: ItemsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvSeries: Tv) {
            with(binding) {
                contentTitle.text = tvSeries.name
                contentReleaseDate.text =
                    LocalDate.parse(tvSeries.firstAirDate).toStringWithPattern(DEFAULT_PATTERN)
                contentOverview.text = tvSeries.overview
                val userScore = tvSeries.voteAverage.toFloat()
                contentNumberUserScore.text = userScore.toString()
                contentRbUserScore.rating = userScore.div(2)
                Glide.with(itemView.context)
                    .load(
                        StringBuilder(TMdbService.IMAGE_BASE_URL).append(ImageConfiguration.POSTER_SIZE[4])
                            .append(tvSeries.posterPath)
                    )
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(contentPoster)
                itemContainer.setOnClickListener { callback.onClickListener(tvSeries.id) }
            }
        }
    }
}
