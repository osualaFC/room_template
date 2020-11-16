package com.raywenderlich.android.librarian.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.raywenderlich.android.librarian.database.dao.BookDao
import com.raywenderlich.android.librarian.database.dao.GenreDao
import com.raywenderlich.android.librarian.database.dao.ReadingListDao
import com.raywenderlich.android.librarian.database.dao.ReviewDao
import com.raywenderlich.android.librarian.model.Book
import com.raywenderlich.android.librarian.model.Genre
import com.raywenderlich.android.librarian.model.ReadingList
import com.raywenderlich.android.librarian.model.Review

@Database(
        entities = [Book::class, Genre::class, Review::class, ReadingList::class],
        version = 1
)
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
                    .build()

        }
    }
}