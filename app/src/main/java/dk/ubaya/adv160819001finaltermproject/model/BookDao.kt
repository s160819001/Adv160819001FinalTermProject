package dk.ubaya.adv160819001finaltermproject.model

import androidx.room.*

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBook(vararg book: Book)

    @Query("SELECT * FROM book_table")
    suspend fun selectAllBook():List<Book>

    @Query("SELECT * FROM BOOK_TABLE WHERE isbn=:isbn")
    suspend fun selectBook(isbn:String):Book

    @Delete
    suspend fun deleteBook(book: Book)
}