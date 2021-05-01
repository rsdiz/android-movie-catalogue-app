package id.rosyid.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.rosyid.moviecatalogue.R
import id.rosyid.moviecatalogue.data.MovieEntity
import id.rosyid.moviecatalogue.databinding.ItemsMoviesBinding
import id.rosyid.moviecatalogue.ui.homepage.ItemsCallback
import id.rosyid.moviecatalogue.utils.FormatPattern.DEFAULT_PATTERN
import id.rosyid.moviecatalogue.utils.toStringWithPattern

class MovieAdapter(private val callback: ItemsCallback) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var listMovies = mutableListOf<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?) {
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
        fun bind(movie: MovieEntity) {
            with(binding) {
                contentTitle.text = movie.title
                contentReleaseDate.text = movie.releaseDate.toStringWithPattern(DEFAULT_PATTERN)
                contentOverview.text = movie.overview
                val userScore = movie.userScore.toFloat().div(10)
                contentNumberUserScore.text = userScore.toString()
                contentRbUserScore.rating = userScore
                Glide.with(itemView.context)
                    .load(movie.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(contentPoster)
                itemContainer.setOnClickListener { callback.onClickListener(movie.movieId) }
            }
        }
    }
}
