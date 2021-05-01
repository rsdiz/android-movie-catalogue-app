package id.rosyid.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.rosyid.moviecatalogue.databinding.ItemsTextListBinding

class ListTextAdapter : RecyclerView.Adapter<ListTextAdapter.ViewHolder>() {
    private var listText = mutableListOf<String>()

    fun setList(list: List<String>?) {
        if (list == null) return
        listText.clear()
        listText.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsTextListBinding =
            ItemsTextListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsTextListBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listText[position])

    override fun getItemCount(): Int = listText.size

    inner class ViewHolder(private val binding: ItemsTextListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(text: String) {
            with(binding) {
                contentName.text = text
            }
        }
    }
}
