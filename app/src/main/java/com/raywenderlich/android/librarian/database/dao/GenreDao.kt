package com.raywenderlich.android.librarian.database.dao

import androidx.room.*
import com.raywenderlich.android.librarian.model.Genre
import com.raywenderlich.android.librarian.model.relations.BooksByGenre

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre")
    fun getGenre(): List<Genre>

    @Query("SELECT * FROM genre WHERE id = :genreId")
    fun getGenreId(genreId: String): Genre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGenre(genre: List<Genre>)

    @Transaction
    @Query("SELECT * FROM genre WHERE id =:genreId")
    fun getBooksByGenre(genreId: String): BooksByGenre

    @Transaction
    @Query("SELECT * FROM genre")
    fun getBooksByGenre(): List<BooksByGenre>
}