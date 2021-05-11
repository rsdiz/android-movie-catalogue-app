package id.rosyid.moviecatalogue.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.rosyid.moviecatalogue.R
import id.rosyid.moviecatalogue.data.entities.Movie
import id.rosyid.moviecatalogue.data.remote.api.TMdbService.Companion.IMAGE_BASE_URL
import id.rosyid.moviecatalogue.databinding.ItemsMoviesBinding
import id.rosyid.moviecatalogue.ui.homepage.ItemsCallback
import id.rosyid.moviecatalogue.utils.FormatPattern.DEFAULT_PATTERN
import id.rosyid.moviecatalogue.utils.ImageConfiguration.POSTER_SIZE
import id.rosyid.moviecatalogue.utils.toStringWithPattern
import java.time.LocalDate

class MovieAdapter(private val callback: ItemsCallback) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var listMovies = mutableListOf<Movie>()

    fun setMovies(movies: List<Movie>?) {
        if (movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsMoviesBinding =
            ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class ViewHolder(private val binding: ItemsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                contentTitle.text = movie.title
                contentReleaseDate.text =
                    LocalDate.parse(movie.releaseDate).toStringWithPattern(DEFAULT_PATTERN)
                contentOverview.text = movie.overview
                val userScore = movie.voteAverage.toFloat()
                contentNumberUserScore.text = userScore.toString()
                contentRbUserScore.rating = userScore.div(2)
                Glide.with(itemView.context)
                    .load(
                        Uri.parse(
                            StringBuilder(IMAGE_BASE_URL).append(POSTER_SIZE[4])
                                .append(movie.posterPath).toString()
                        )
                    )
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(contentPoster)
                itemContainer.setOnClickListener { callback.onClickListener(movie.id) }
            }
        }
    }
}
