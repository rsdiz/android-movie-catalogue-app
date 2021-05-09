package id.rosyid.moviecatalogue.ui.homepage.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.rosyid.moviecatalogue.R
import id.rosyid.moviecatalogue.adapter.MovieAdapter
import id.rosyid.moviecatalogue.databinding.FragmentMoviesBinding
import id.rosyid.moviecatalogue.ui.detail.DetailActivity
import id.rosyid.moviecatalogue.ui.homepage.ItemsCallback
import id.rosyid.moviecatalogue.utils.Resource
import id.rosyid.moviecatalogue.utils.autoCleared

@AndroidEntryPoint
class MoviesFragment : Fragment(), ItemsCallback {
    private var viewBinding: FragmentMoviesBinding by autoCleared()
    private val viewModel: MoviesViewModel by viewModels()

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
            viewModel.listMovies.observe(
                viewLifecycleOwner,
                { listMovies ->
                    when (listMovies.status) {
                        Resource.Status.LOADING -> {
                            showLoading(true)
                        }
                        Resource.Status.SUCCESS -> {
                            showLoading(false)
                            if (listMovies.data != null) {
                                val movies = listMovies.data
                                val movieAdapter = MovieAdapter(this)
                                movieAdapter.setMovies(movies)

                                with(viewBinding.rvMovies) {
                                    layoutManager = LinearLayoutManager(context)
                                    setHasFixedSize(true)
                                    adapter = movieAdapter
                                }
                            } else showMessage(true, resources.getString(R.string.data_empty))
                        }
                        Resource.Status.ERROR -> {
                            showMessage(true, listMovies?.message.toString())
                        }
                    }
                }
            )
        }
    }

    private fun showLoading(state: Boolean) {
        with(viewBinding) {
            if (state) {
                rvMovies.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                message.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                rvMovies.visibility = View.VISIBLE
                message.visibility = View.GONE
            }
        }
    }

    private fun showMessage(state: Boolean, data: String) {
        with(viewBinding) {
            if (state) {
                rvMovies.visibility = View.GONE
                progressBar.visibility = View.GONE
                message.visibility = View.VISIBLE
                message.text = data
            } else {
                progressBar.visibility = View.VISIBLE
                rvMovies.visibility = View.GONE
                message.visibility = View.GONE
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
