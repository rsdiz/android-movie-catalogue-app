package id.rosyid.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.rosyid.moviecatalogue.R
import id.rosyid.moviecatalogue.data.TvEntity
import id.rosyid.moviecatalogue.databinding.ItemsMoviesBinding
import id.rosyid.moviecatalogue.ui.homepage.ItemsCallback

class TvAdapter(private val callback: ItemsCallback) :
    RecyclerView.Adapter<TvAdapter.ViewHolder>() {
    private var listTvSeries = mutableListOf<TvEntity>()

    fun setTvSeries(tvSeries: List<TvEntity>?) {
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
        fun bind(tvSeries: TvEntity) {
            with(binding) {
                contentTitle.text = tvSeries.title
                contentReleaseDate.visibility = View.GONE
                contentOverview.text = tvSeries.overview
                val userScore = tvSeries.userScore.toFloat().div(10)
                contentNumberUserScore.text = userScore.toString()
                contentRbUserScore.rating = userScore
                Glide.with(itemView.context)
                    .load(tvSeries.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(contentPoster)
                itemContainer.setOnClickListener { callback.onClickListener(tvSeries.tvId) }
            }
        }
    }
}
