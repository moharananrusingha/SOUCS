package com.oit.soucs.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.oit.soucs.util.DATABASE_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Database(entities = [Tile::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun tileDao(): TileDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            //pre-populate data
                            val tileList: MutableList<Tile> by lazy {
                                mutableListOf(Tile("1",1,null,"Acceptance","Lorem Ipsum Description"),Tile("2",2,null,"Exclusion","Lorem Ipsum Description"),
                                    Tile("3",3,null,"Fear of Difference","Lorem Ipsum Description"),Tile("4",4,null,"Avoidance","Lorem Ipsum Description"),
                                    Tile("5",5,null,"Lasting of Friendship","Lorem Ipsum Description"),Tile("6",6,null,"Situational Friendship","Lorem Ipsum Description"),
                                    Tile("7",7,null,"Meaningful Inclusion","Lorem Ipsum Description"),
                                    Tile("8",8,null,"Tolerance","Lorem Ipsum Description"),Tile("9",9,null,"Inclusion","Lorem Ipsum Description")
                                )
                            }
                            CoroutineScope(Dispatchers.IO).launch {
                                instance?.tileDao()?.insertAll(tileList)
                            }
                        }
                    }
                )
                .build()
        }
    }
}