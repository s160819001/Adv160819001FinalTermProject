package dk.ubaya.adv160819001finaltermproject.util

import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.room.Room
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.model.LibraryDatabase
import java.lang.Exception
val DB_NAME="librarydb"

fun buildDB(context: Context):LibraryDatabase{
    val db = Room.databaseBuilder(context, LibraryDatabase::class.java, DB_NAME).build()
    return db
}

@BindingAdapter("android:imageUrl","android:progressBar")
fun bindImage(view: ImageView,url:String,pb:ProgressBar){
    Log.e("bindingadapter","masuk")
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(view,object :Callback{
            override fun onSuccess() {
                pb.visibility=View.GONE
            }

            override fun onError(e: Exception?) {
            }

        })
}