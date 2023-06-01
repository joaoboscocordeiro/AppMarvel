package br.com.superhero.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.superhero.databinding.ItemParentDetailBinding
import br.com.superhero.framework.imageloader.ImageLoader

/**
 * Created by João Bosco on 24/04/2023.
 */
class DetailParentAdapter(
    private val detailParentList: List<DetailParentVE>,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<DetailParentAdapter.DetailParentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailParentViewHolder {
        return DetailParentViewHolder.create(parent, imageLoader)
    }

    override fun onBindViewHolder(holder: DetailParentViewHolder, position: Int) {
        holder.bind(detailParentList[position])
    }

    override fun getItemCount() = detailParentList.size

    class DetailParentViewHolder(
        itemBinding: ItemParentDetailBinding,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private val txtItemCategory: TextView = itemBinding.txtTitleItemDetail
        private val rvChildDetail: RecyclerView = itemBinding.rvChildDetail

        fun bind(detailParentVE: DetailParentVE) {
            txtItemCategory.text = itemView.context.getString(detailParentVE.categoryStringResId)
            rvChildDetail.run {
                setHasFixedSize(true)
                adapter = DetailChildAdapter(detailParentVE.detailChildList, imageLoader)
            }
        }

        companion object {
            fun create(
                parent: ViewGroup,
                imageLoader: ImageLoader
            ): DetailParentViewHolder {
                val itemBinding = ItemParentDetailBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return DetailParentViewHolder(itemBinding, imageLoader)
            }
        }
    }
}