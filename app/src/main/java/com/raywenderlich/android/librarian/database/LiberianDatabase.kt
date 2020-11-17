package com.raywenderlich.android.librarian.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raywenderlich.android.librarian.database.converters.BookIdsConverter
import com.raywenderlich.android.librarian.database.converters.DateConverter
import com.raywenderlich.android.librarian.database.converters.ReadingEntryConverter
import com.raywenderlich.android.librarian.database.dao.BookDao
import com.raywenderlich.android.librarian.database.dao.GenreDao
import com.raywenderlich.android.librarian.database.dao.ReadingListDao
import com.raywenderlich.android.librarian.database.dao.ReviewDao
import com.raywenderlich.android.librarian.database.migration.migration_1_2
import com.raywenderlich.android.librarian.database.migration.migration_2_3
import com.raywenderlich.android.librarian.database.migration.migration_3_4
import com.raywenderlich.android.librarian.model.Book
import com.raywenderlich.android.librarian.model.Genre
import com.raywenderlich.android.librarian.model.ReadingList
import com.raywenderlich.android.librarian.model.Review

@Database(
        entities = [Book::class, Genre::class, Review::class, ReadingList::class],
        version = 4
)

@TypeConverters(DateConverter::class, ReadingEntryConverter::class, BookIdsConverter::class)
abstract class LiberianDatabase : RoomDatabase(){

    abstract fun bookDao(): BookDao
    abstract fun genreDao(): GenreDao
    abstract fun readingListDao(): ReadingListDao
    abstract fun reviewDao(): ReviewDao

    companion object{
        private const val DATABASE_NAME ="Liberian"

        fun buildDatabase(context: Context) : LiberianDatabase{
            return Room.databaseBuilder(
                    context,
                    LiberianDatabase::class.java,
                    DATABASE_NAME
            )
                    .allowMainThreadQueries()
                    .addMigrations(migration_1_2, migration_2_3, migration_3_4)
                    .build()

        }
    }
}