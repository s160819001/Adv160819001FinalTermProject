package dk.ubaya.adv160819001finaltermproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import dk.ubaya.adv160819001finaltermproject.model.Book
import dk.ubaya.adv160819001finaltermproject.model.LibraryDatabase
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
            val db = Room.databaseBuilder(getApplication(), LibraryDatabase::class.java,"librarydb").build()
            db.bookDao().insertAllBook(book)
        }
    }

    fun fetch(isbn:String){
        Log.e("masuk fetch","masuk ini")
        launch {
            val db = Room.databaseBuilder(getApplication(), LibraryDatabase::class.java,"librarydb").build()
            bookLD.value=db.bookDao().selectBook(isbn)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job+ Dispatchers.Main
}