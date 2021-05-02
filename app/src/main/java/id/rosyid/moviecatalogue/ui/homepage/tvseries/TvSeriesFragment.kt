package id.rosyid.moviecatalogue.ui.homepage.tvseries

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.rosyid.moviecatalogue.R
import id.rosyid.moviecatalogue.adapter.TvAdapter
import id.rosyid.moviecatalogue.data.TvEntity
import id.rosyid.moviecatalogue.databinding.FragmentTvSeriesBinding
import id.rosyid.moviecatalogue.ui.detail.DetailActivity
import id.rosyid.moviecatalogue.ui.homepage.ItemsCallback
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
        showLoading(true)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvSeriesViewModel::class.java]
            val tvSeries = viewModel.getTvSeries()

            val tvAdapter = TvAdapter(this)
            tvAdapter.setTvSeries(tvSeries)

            with(viewBinding.rvTvSeries) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }

            if (tvAdapter.itemCount > 0) showLoading(false)
            else showMessage(true, resources.getString(R.string.data_empty))
        }
    }

    private fun showLoading(state: Boolean) {
        with(viewBinding) {
            if (state) {
                rvTvSeries.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                message.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                rvTvSeries.visibility = View.VISIBLE
                message.visibility = View.GONE
            }
        }
    }

    private fun showMessage(state: Boolean, data: String) {
        with(viewBinding) {
            if (state) {
                rvTvSeries.visibility = View.GONE
                progressBar.visibility = View.GONE
                message.visibility = View.VISIBLE
                message.text = data
            } else {
                progressBar.visibility = View.VISIBLE
                rvTvSeries.visibility = View.GONE
                message.visibility = View.GONE
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
