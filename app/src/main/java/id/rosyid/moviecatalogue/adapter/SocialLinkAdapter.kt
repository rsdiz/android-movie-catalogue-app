package id.rosyid.moviecatalogue.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.rosyid.moviecatalogue.R
import id.rosyid.moviecatalogue.data.SocialLinkEntity
import id.rosyid.moviecatalogue.databinding.ItemsSocialLinkBinding
import id.rosyid.moviecatalogue.utils.TypeSocialLink

class SocialLinkAdapter : RecyclerView.Adapter<SocialLinkAdapter.ViewHolder>() {
    private var listSocialLink = mutableListOf<SocialLinkEntity>()

    fun setList(list: List<SocialLinkEntity>?) {
        if (list == null) return
        listSocialLink.clear()
        listSocialLink.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsSocialLinkBinding =
            ItemsSocialLinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsSocialLinkBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listSocialLink[position])

    override fun getItemCount(): Int = listSocialLink.size

    inner class ViewHolder(private val binding: ItemsSocialLinkBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = itemView.context

        fun bind(socialLink: SocialLinkEntity) {
            with(binding) {
                imageButton.setImageResource(
                    when (socialLink.name) {
                        TypeSocialLink.Facebook -> R.drawable.ic_facebook
                        TypeSocialLink.Twitter -> R.drawable.ic_twitter
                        TypeSocialLink.Instagram -> R.drawable.ic_instagram
                        TypeSocialLink.JustWatch -> R.drawable.ic_justwatch
                        TypeSocialLink.Homepage -> R.drawable.ic_link
                    }
                )
                imageButton.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(socialLink.link)
                    context.startActivity(intent)
                }
            }
        }
    }
}
