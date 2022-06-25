package dk.ubaya.adv160819001finaltermproject.viewmodel

import android.app.Application
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

class BookListViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val booksLD = MutableLiveData<List<Book>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    fun refresh() {
        loadingErrorLD.value=false
        loadingLD.value=true

        launch {
            val db = Room.databaseBuilder(getApplication(), LibraryDatabase::class.java,"librarydb").build()
            booksLD.value=db.bookDao().selectAllBook()
        }
    }

    fun delete(book: Book){
        launch {
            val db = Room.databaseBuilder(getApplication(),LibraryDatabase::class.java,"librarydb").build()
            db.bookDao().deleteBook(book)
            booksLD.value=db.bookDao().selectAllBook()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job+ Dispatchers.Main
}