package id.rosyid.moviecatalogue.ui.homepage.tvseries

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.rosyid.moviecatalogue.adapter.TvAdapter
import id.rosyid.moviecatalogue.databinding.FragmentTvSeriesBinding
import id.rosyid.moviecatalogue.ui.detail.DetailActivity
import id.rosyid.moviecatalogue.ui.homepage.ItemsCallback
import id.rosyid.moviecatalogue.utils.TvSeriesData
import id.rosyid.moviecatalogue.utils.autoCleared

class TvSeriesFragment : Fragment(), ItemsCallback {
    private var viewBinding: FragmentTvSeriesBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentTvSeriesBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val tvSeries = TvSeriesData.generateTvSeries()
            val tvAdapter = TvAdapter(this)
            tvAdapter.setTvSeries(tvSeries)

            with(viewBinding.rvTvSeries) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }

    override fun onClickListener(id: Int) {
        if (activity != null) {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, id)
            intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.TYPE_TVSERIES)
            requireContext().startActivity(intent)
        }
    }
}
