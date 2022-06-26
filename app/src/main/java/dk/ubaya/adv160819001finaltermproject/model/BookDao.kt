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

    @Query("UPDATE book_table SET title=:title, author=:author, publisher=:publisher, year=:year, synopsis=:synopsis, location=:location, image=:image WHERE isbn = :isbn")
    suspend fun update(title:String, author:String,publisher:String,year:String,synopsis:String,location:String,image:String, isbn:String)

    @Delete
    suspend fun deleteBook(book: Book)
}