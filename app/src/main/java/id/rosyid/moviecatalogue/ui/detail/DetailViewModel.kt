package id.rosyid.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import id.rosyid.moviecatalogue.data.BaseEntity

class DetailViewModel : ViewModel() {
    private var id = -1
    private lateinit var type: String

    fun setSelectedId(id: Int, type: String) {
        this.id = id
        this.type = type
    }

    fun getItemDetail(): BaseEntity {
        lateinit var itemDetail: BaseEntity

        return itemDetail
    }
}
