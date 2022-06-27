package dk.ubaya.adv160819001finaltermproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dk.ubaya.adv160819001finaltermproject.model.Book
import dk.ubaya.adv160819001finaltermproject.model.Thesis
import dk.ubaya.adv160819001finaltermproject.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ThesisListViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val thesisLD = MutableLiveData<List<Thesis>>()
    private var job = Job()

    fun refresh(){
        launch {
            val db = buildDB(getApplication())
            thesisLD.value=db.thesisDao().selectAllThesis()
        }
    }
    fun addThesis(thesis: Thesis){

        launch {
            val db = buildDB(getApplication())
            db.thesisDao().insertAllThesis(thesis)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job+ Dispatchers.Main
}