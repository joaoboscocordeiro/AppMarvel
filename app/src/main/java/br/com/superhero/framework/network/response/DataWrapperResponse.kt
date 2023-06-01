package br.com.superhero.framework.network.response

import com.google.gson.annotations.SerializedName

/**
 * Created by João Bosco on 05/09/2022.
 */
data class DataWrapperResponse<T>(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    val data: DataContainerResponse<T>
)
