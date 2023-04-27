package br.com.superhero.framework.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import br.com.superhero.R

/**
 * Created by João Bosco on 20/04/2023.
 */
interface ImageLoader {
    fun load(
        imageView: ImageView,
        imageUrl: String,
        @DrawableRes placeholder: Int = R.drawable.ic_img_placeholder,
        @DrawableRes fallback: Int = R.drawable.ic_img_loading_error
    )
}