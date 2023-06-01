package br.com.superhero.presentation.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.superhero.databinding.ItemCharacterBinding
import br.com.superhero.framework.imageloader.ImageLoader
import br.com.superhero.presentation.common.GenericViewHolder

/**
 * Created by João Bosco on 01/06/2023.
 */
class FavoriteViewHolder(
    itemBinding: ItemCharacterBinding,
    private val imageLoader: ImageLoader
) : GenericViewHolder<FavoriteItem>(itemBinding) {

    private val txtName: TextView = itemBinding.textName
    private val imgCharacter: ImageView = itemBinding.imageCharacter

    override fun bind(data: FavoriteItem) {
        txtName.text = data.name
        imageLoader.load(imgCharacter, data.imageUrl)
    }

    companion object {
        fun create(parent: ViewGroup, imageLoader: ImageLoader): FavoriteViewHolder {
            val itemBinding = ItemCharacterBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return FavoriteViewHolder(itemBinding, imageLoader)
        }
    }
}