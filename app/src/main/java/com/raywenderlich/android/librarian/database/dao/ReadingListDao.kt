package com.raywenderlich.android.librarian.database.dao

import androidx.room.*
import com.raywenderlich.android.librarian.model.ReadingList
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReadingList(readingList: ReadingList)

    @Query("SELECT * FROM readingList")
    fun getReadingList():List<ReadingList>

    @Query("SELECT * FROM readingList")
    fun getReadingListFlow(): Flow<List<ReadingList>>

    @Delete
    fun removeReadingList(readingList: ReadingList)
}