package com.raywenderlich.android.librarian.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raywenderlich.android.librarian.model.Genre

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre")
    fun getGenre(): List<Genre>

    @Query("SELECT * FROM genre WHERE id = :genreId")
    fun getGenreId(genreId: String): Genre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGenre(genre: List<Genre>)
}