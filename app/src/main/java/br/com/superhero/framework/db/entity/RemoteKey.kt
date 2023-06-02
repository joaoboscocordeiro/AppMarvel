package br.com.superhero.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.core.data.DbConstants

/**
 * Created by João Bosco on 01/06/2023.
 */

@Entity(tableName = DbConstants.REMOTE_KEYS_TABLE_NAME)
data class RemoteKey(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(name = DbConstants.REMOTE_KEYS_COLUMN_INFO_OFFSET)
    val nextOffSet: Int?
)
