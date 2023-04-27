package br.com.superhero.framework.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import javax.inject.Inject

/**
 * Created by João Bosco on 20/04/2023.
 */
class GlideImageLoader @Inject constructor() : ImageLoader {
    override fun load(imageView: ImageView, imageUrl: String, placeholder: Int, fallback: Int) {
        Glide.with(imageView.rootView)
            .load(imageUrl)
            .placeholder(placeholder)
            .fallback(fallback)
            .into(imageView)
    }
}