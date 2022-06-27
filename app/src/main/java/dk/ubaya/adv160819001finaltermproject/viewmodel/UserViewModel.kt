package dk.ubaya.adv160819001finaltermproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dk.ubaya.adv160819001finaltermproject.model.Book
import dk.ubaya.adv160819001finaltermproject.model.User
import dk.ubaya.adv160819001finaltermproject.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val userLD = MutableLiveData<User>()
    private var job = Job()

    fun addUser(user: User){

        launch {
            val db = buildDB(getApplication())
            db.userDao().insertAllUser(user)
        }
    }

    fun check(user:User){
        launch {
            val db = buildDB(getApplication())
            val userFromDB=db.userDao().selectUser(user.email)
            if(userFromDB!=null && userFromDB.password==user.password){
                userLD.value=userFromDB
            }else{
                userLD.value=User("!@#$%^&*()_","!@#$%^&*()_","!@#$%^&*()_")
            }
        }
    }
    fun fetch(email: String){
        launch {
            val db = buildDB(getApplication())
            userLD.value=db.userDao().selectUser(email)
        }
    }
    fun update(name:String,email:String,password:String) {
        launch {
            val db = buildDB(getApplication())
            db.userDao().updateUser(name,password,email)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job+ Dispatchers.Main
}