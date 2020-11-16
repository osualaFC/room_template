package com.raywenderlich.android.librarian.database.dao

import androidx.room.*
import com.raywenderlich.android.librarian.model.ReadingList
import com.raywenderlich.android.librarian.model.Review
import com.raywenderlich.android.librarian.model.relations.BookReview

@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReview(review: Review)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateReview(review: Review)

    @Query("SELECT * FROM review")
    fun getReviews():List<Review>

    @Query("SELECT * FROM review WHERE id =:reviewId")
    fun getReviewById(reviewId:String): Review

    @Delete
    fun removeReview(review: Review)
}