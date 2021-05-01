package id.rosyid.moviecatalogue.ui.homepage.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.rosyid.moviecatalogue.adapter.MovieAdapter
import id.rosyid.moviecatalogue.databinding.FragmentMoviesBinding
import id.rosyid.moviecatalogue.ui.detail.DetailActivity
import id.rosyid.moviecatalogue.ui.homepage.ItemsCallback
import id.rosyid.moviecatalogue.utils.MoviesData
import id.rosyid.moviecatalogue.utils.autoCleared

class MoviesFragment : Fragment(), ItemsCallback {
    private var viewBinding: FragmentMoviesBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movies = MoviesData.generateMovies()
            val movieAdapter = MovieAdapter(this)
            movieAdapter.setMovies(movies)

            with(viewBinding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onClickListener(id: Int) {
        if (activity != null) {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, id)
            intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.TYPE_MOVIES)
            requireContext().startActivity(intent)
        }
    }
}
