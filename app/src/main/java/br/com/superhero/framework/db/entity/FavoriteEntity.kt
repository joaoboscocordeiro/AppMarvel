package br.com.superhero.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.core.data.DbConstants
import br.com.core.domain.model.Character

/**
 * Created by João Bosco on 09/05/2023.
 */

@Entity(tableName = DbConstants.FAVORITES_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = DbConstants.FAVORITES_COLUMN_INFO_ID)
    val id: Int,
    @ColumnInfo(name = DbConstants.FAVORITES_COLUMN_INFO_NAME)
    val name: String,
    @ColumnInfo(name = DbConstants.FAVORITES_COLUMN_INFO_IMAGE_URL)
    val imageUrl: String
)

fun List<FavoriteEntity>.toCharactersModel() = map {
    Character(it.id, it.name, it.imageUrl)
}
