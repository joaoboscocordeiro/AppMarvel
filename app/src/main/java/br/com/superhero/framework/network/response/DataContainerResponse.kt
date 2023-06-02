package br.com.superhero.framework.network.response

import com.google.gson.annotations.SerializedName

/**
 * Created by João Bosco on 05/09/2022.
 */
data class DataContainerResponse<T>(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("results")
    val results: List<T>
)
