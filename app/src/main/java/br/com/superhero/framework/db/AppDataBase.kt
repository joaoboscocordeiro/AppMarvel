package br.com.superhero.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.superhero.framework.db.dao.CharacterDao
import br.com.superhero.framework.db.dao.FavoriteDao
import br.com.superhero.framework.db.dao.RemoteKeyDao
import br.com.superhero.framework.db.entity.CharacterEntity
import br.com.superhero.framework.db.entity.FavoriteEntity
import br.com.superhero.framework.db.entity.RemoteKey

/**
 * Created by João Bosco on 09/05/2023.
 */

@Database(
    entities = [
        FavoriteEntity::class,
        CharacterEntity::class,
        RemoteKey::class
    ], version = 1, exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}