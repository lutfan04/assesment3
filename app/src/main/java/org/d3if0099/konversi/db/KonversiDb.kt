package org.d3if0099.konversi.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [KonversiEntity::class], version = 2, exportSchema = false)
abstract class KonversiDb : RoomDatabase() {
    abstract val dao: KonversiDao
    companion object {
        @Volatile
        private var INSTANCE: KonversiDb? = null
        fun getInstance(context: Context): KonversiDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        KonversiDb::class.java,
                        "Konversi.db"
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
