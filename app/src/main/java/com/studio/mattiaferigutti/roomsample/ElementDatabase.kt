package com.studio.mattiaferigutti.roomsample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Element::class], version = 1, exportSchema = false)
abstract class ElementDatabase : RoomDatabase() {

    abstract val elementDatabaseDao: ElementDao

    companion object {
        @Volatile
        private var INSTANCE: ElementDatabase? = null

        fun getInstance(context: Context): ElementDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ElementDatabase::class.java,
                        "element_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}