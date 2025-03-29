// Database.kt
package com.ilyes.littlelemon

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import org.jetbrains.annotations.NotNull

@Entity(tableName = "menu_items")
data class MenuItemEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String
)

@Dao
interface MenuDao {
    @Insert
    fun insertMenuItems(menuItems: List<MenuItemEntity>)

    @Query("SELECT * FROM menu_items")
    fun getAllMenuItems(): LiveData<List<MenuItemEntity>>

    @Query("SELECT (SELECT COUNT(*) FROM menu_items) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [MenuItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao
}

fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "menu_db").build()
}
