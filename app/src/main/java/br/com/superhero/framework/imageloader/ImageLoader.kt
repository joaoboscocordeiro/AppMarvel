package br.com.superhero.framework.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes

/**
 * Created by João Bosco on 20/04/2023.
 */
interface ImageLoader {
    fun load(imageView: ImageView, imageUrl: String, @DrawableRes fallback: Int)
}