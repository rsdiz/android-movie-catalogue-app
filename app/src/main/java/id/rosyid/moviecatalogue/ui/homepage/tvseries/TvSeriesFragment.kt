package id.rosyid.moviecatalogue.ui.homepage.tvseries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.rosyid.moviecatalogue.databinding.FragmentTvSeriesBinding
import id.rosyid.moviecatalogue.utils.autoCleared

class TvSeriesFragment : Fragment() {
    private var viewBinding: FragmentTvSeriesBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentTvSeriesBinding.inflate(inflater, container, false)
        return viewBinding.root
    }
}
