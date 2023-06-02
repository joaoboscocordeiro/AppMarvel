package br.com.superhero.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.core.data.DbConstants

/**
 * Created by João Bosco on 01/06/2023.
 */

@Entity(tableName = DbConstants.CHARACTERS_TABLE_NAME)
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val autoId: Int = 0,
    @ColumnInfo(name = DbConstants.CHARACTERS_COLUMN_INFO_ID)
    val id: Int,
    @ColumnInfo(name = DbConstants.CHARACTERS_COLUMN_INFO_NAME)
    val name: String,
    @ColumnInfo(name = DbConstants.CHARACTERS_COLUMN_INFO_IMAGE_URL)
    val imageUrl: String
)