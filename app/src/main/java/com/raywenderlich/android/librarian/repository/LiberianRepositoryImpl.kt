package com.raywenderlich.android.librarian.repository

import com.raywenderlich.android.librarian.database.dao.BookDao
import com.raywenderlich.android.librarian.database.dao.GenreDao
import com.raywenderlich.android.librarian.database.dao.ReadingListDao
import com.raywenderlich.android.librarian.database.dao.ReviewDao
import com.raywenderlich.android.librarian.model.Book
import com.raywenderlich.android.librarian.model.Genre
import com.raywenderlich.android.librarian.model.ReadingList
import com.raywenderlich.android.librarian.model.Review
import com.raywenderlich.android.librarian.model.relations.BookAndGenre
import com.raywenderlich.android.librarian.model.relations.BookReview
import com.raywenderlich.android.librarian.model.relations.BooksByGenre
import com.raywenderlich.android.librarian.model.relations.ReadingListsWithBooks

class LiberianRepositoryImpl(
        private val bookDao: BookDao,
        private val genreDao: GenreDao,
        private val readingListDao: ReadingListDao,
        private val reviewDao: ReviewDao
): LiberianRepository {
    override fun addBook(book: Book) = bookDao.addBook(book)

    override fun getBooks(): List<BookAndGenre> = bookDao.getBooks()

    override fun getBookById(bookId:String): Book = bookDao.getBookById(bookId)

    override fun removeBook(book: Book) = bookDao.removeBook(book)

    override fun getGenre(): List<Genre> = genreDao.getGenre()

    override fun getGenreById(genreId: String): Genre = genreDao.getGenreId(genreId)

    override fun addGenre(genre: List<Genre>) = genreDao.addGenre(genre)

    override fun addReview(review: Review) = reviewDao.addReview(review)

    override fun getReviews(): List<BookReview> = reviewDao.getReviews().map{
        BookReview(it, bookDao.getBookById(it.bookId))
    }

    override fun getReviewById(reviewId: String): BookReview {
        val review = reviewDao.getReviewById(reviewId)

       return  BookReview(review, bookDao.getBookById(review.bookId))
    }

    override fun deleteReview(review: Review) = reviewDao.removeReview(review)

    override fun updateReview(review: Review) = reviewDao.updateReview(review)

    override fun addReadingList(readingList: ReadingList) = readingListDao.addReadingList(readingList)

    override fun getReadingList(): List<ReadingListsWithBooks> = readingListDao.getReadingList().map{
        ReadingListsWithBooks(it.id, it.name, emptyList())
    }

    override fun deleteReadingList(readingList: ReadingList) = readingListDao.removeReadingList(readingList)

    override fun getBooksByGenre(genreId: String): List<BookAndGenre> =
            genreDao.getBooksByGenre(genreId).let{booksByGenre ->  
                val books = booksByGenre.books ?: return emptyList()

                return books.map{BookAndGenre(it, booksByGenre.genre)}
            }
}