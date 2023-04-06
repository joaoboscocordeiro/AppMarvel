package br.com.superhero.framework.network.response

import com.google.gson.annotations.SerializedName

/**
 * Created by João Bosco on 05/09/2022.
 * e-mail - Support: ti.junior@gmail.com
 */
data class DataContainerResponse(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("results")
    val results: List<CharacterResponse>
)
