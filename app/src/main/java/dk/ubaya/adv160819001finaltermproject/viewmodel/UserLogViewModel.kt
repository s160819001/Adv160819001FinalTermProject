package dk.ubaya.adv160819001finaltermproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dk.ubaya.adv160819001finaltermproject.model.UserLogin
import dk.ubaya.adv160819001finaltermproject.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserLogViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val userLogLD = MutableLiveData<UserLogin>()
    private var job = Job()

    fun addLog(userLogin: UserLogin){
        launch {
            val db = buildDB(getApplication())
            db.userLogDao().insertUserLog(userLogin)
        }
    }

    fun fetch(){
        launch {
            val db = buildDB(getApplication())
            userLogLD.value=db.userLogDao().selectUserLog()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job+ Dispatchers.Main
}