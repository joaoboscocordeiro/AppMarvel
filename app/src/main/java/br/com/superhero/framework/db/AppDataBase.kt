package br.com.superhero.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.superhero.framework.db.dao.FavoriteDao
import br.com.superhero.framework.db.entity.FavoriteEntity

/**
 * Created by João Bosco on 09/05/2023.
 */

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}