package br.com.superhero.presentation.detail

import androidx.annotation.StringRes

/**
 * Created by João Bosco on 24/04/2023.
 */
data class DetailChildVE(
    val id: Int,
    val imageUrl: String
)

data class DetailParentVE(
    @StringRes
    val categoryStringResId: Int,
    val detailChildList: List<DetailChildVE> = listOf()
)