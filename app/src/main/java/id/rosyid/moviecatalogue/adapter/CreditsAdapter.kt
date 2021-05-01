package id.rosyid.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.rosyid.moviecatalogue.data.CreditEntity
import id.rosyid.moviecatalogue.databinding.ItemsCreditsBinding

class CreditsAdapter : RecyclerView.Adapter<CreditsAdapter.ViewHolder>() {
    private var listCredits = mutableListOf<CreditEntity>()

    fun setList(list: List<CreditEntity>?) {
        if (list == null) return
        listCredits.clear()
        listCredits.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsCreditsBinding =
            ItemsCreditsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsCreditsBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listCredits[position])

    override fun getItemCount(): Int = listCredits.size

    inner class ViewHolder(private val binding: ItemsCreditsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(credits: CreditEntity) {
            with(binding) {
                contentName.text = credits.name
                contentRole.text = credits.position
            }
        }
    }
}
