package id.rosyid.moviecatalogue.ui.homepage.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.rosyid.moviecatalogue.adapter.MovieAdapter
import id.rosyid.moviecatalogue.databinding.FragmentMoviesBinding
import id.rosyid.moviecatalogue.utils.MoviesData
import id.rosyid.moviecatalogue.utils.autoCleared

class MoviesFragment : Fragment() {
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
            val movieAdapter = MovieAdapter()
            movieAdapter.setMovies(movies)

            with(viewBinding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}
