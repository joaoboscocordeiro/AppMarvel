package br.com.core.data.mapper

import br.com.core.data.StorageConstants
import br.com.core.domain.model.SortingTypeEnum
import javax.inject.Inject

/**
 * Created by João Bosco on 06/06/2023.
 */
class SortingMapper @Inject constructor() {

    fun mapIdPair(sorting: String): Pair<String, String> {
        val nameAscending =
            SortingTypeEnum.ORDER_BY_NAME.value to SortingTypeEnum.ORDER_ASCENDING.value
        val nameDescending =
            SortingTypeEnum.ORDER_BY_NAME.value to SortingTypeEnum.ORDER_DESCENDING.value
        val modifiedAscending =
            SortingTypeEnum.ORDER_BY_MODIFIED.value to SortingTypeEnum.ORDER_ASCENDING.value
        val modifiedDescending =
            SortingTypeEnum.ORDER_BY_MODIFIED.value to SortingTypeEnum.ORDER_DESCENDING.value

        return when (sorting) {
            StorageConstants.ORDER_BY_NAME_ASCENDING -> nameAscending
            StorageConstants.ORDER_BY_NAME_DESCENDING -> nameDescending
            StorageConstants.ORDER_BY_MODIFIED_ASCENDING -> modifiedAscending
            StorageConstants.ORDER_BY_MODIFIED_DESCENDING -> modifiedDescending
            else -> nameAscending
        }
    }

    fun mapFromPair(sortingPair: Pair<String, String>): String {
        val orderBy = sortingPair.first
        val order = sortingPair.second

        return when (orderBy) {
            SortingTypeEnum.ORDER_BY_NAME.value -> when (order) {
                SortingTypeEnum.ORDER_ASCENDING.value -> StorageConstants.ORDER_BY_NAME_ASCENDING
                SortingTypeEnum.ORDER_DESCENDING.value -> StorageConstants.ORDER_BY_NAME_DESCENDING
                else -> StorageConstants.ORDER_BY_NAME_ASCENDING
            }
            SortingTypeEnum.ORDER_BY_MODIFIED.value -> when (order) {
                SortingTypeEnum.ORDER_ASCENDING.value -> StorageConstants.ORDER_BY_MODIFIED_ASCENDING
                SortingTypeEnum.ORDER_DESCENDING.value -> StorageConstants.ORDER_BY_MODIFIED_DESCENDING
                else -> StorageConstants.ORDER_BY_MODIFIED_ASCENDING
            }
            else -> StorageConstants.ORDER_BY_NAME_ASCENDING
        }
    }
}