package dk.ubaya.adv160819001finaltermproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import dk.ubaya.adv160819001finaltermproject.model.Book
import dk.ubaya.adv160819001finaltermproject.model.LibraryDatabase
import dk.ubaya.adv160819001finaltermproject.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BookDetailViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val bookLD = MutableLiveData<Book>()
    private val job = Job()

    fun addBook(book: Book){

        launch {
            val db = buildDB(getApplication())
            db.bookDao().insertAllBook(book)
        }
    }

    fun fetch(isbn:String){
        launch {
            val db = buildDB(getApplication())
            bookLD.value=db.bookDao().selectBook(isbn)
        }
    }

    fun update(title:String, author:String,publisher:String,year:String,synopsis:String,location:String,image:String, isbn:String) {
        launch {
            val db = buildDB(getApplication())
            db.bookDao().update(title, author,publisher,year,synopsis, location, image, isbn)
        }
    }

    fun delete(book: Book){
        launch {
            val db = buildDB(getApplication())
            db.bookDao().deleteBook(book)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job+ Dispatchers.Main
}