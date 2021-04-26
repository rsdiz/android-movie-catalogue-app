package id.rosyid.moviecatalogue.ui.homepage.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.rosyid.moviecatalogue.databinding.FragmentMoviesBinding
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
}
