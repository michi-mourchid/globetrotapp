package com.android.flagcountryapp.database

/*import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.flagcountryapp.Converters
import com.android.flagcountryapp.models.Pays
import com.android.flagcountryapp.dao.CountryDao

@Database(entities = [Pays::class], version = 1, exportSchema = false)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "countries_database"
                ).build().also { instance = it }
            }
    }
}*/
