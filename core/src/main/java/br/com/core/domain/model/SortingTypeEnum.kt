package br.com.core.domain.model

/**
 * Created by João Bosco on 06/06/2023.
 */


enum class SortingTypeEnum(val value: String) {
    ORDER_BY_NAME("name"),
    ORDER_BY_MODIFIED("modified"),
    ORDER_ASCENDING("ascending"),
    ORDER_DESCENDING("descending")
}