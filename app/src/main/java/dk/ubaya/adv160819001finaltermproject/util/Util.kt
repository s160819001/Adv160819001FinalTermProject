package dk.ubaya.adv160819001finaltermproject.util

import android.content.Context
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.model.LibraryDatabase
import java.lang.Exception
val DB_NAME="librarydb"

fun buildDB(context: Context):LibraryDatabase{
    val db = Room.databaseBuilder(context, LibraryDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .build()
    return db
}

val MIGRATION_1_2 = object :Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE user_log_table ADD COLUMN timestamp TEXT DEFAULT null")
    }
}

@BindingAdapter("android:imageUrl","android:progressBar")
fun bindImage(view: ImageView,url:String,pb:ProgressBar?){
    Log.e("bindingadapter","masuk")
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(view,object :Callback{
            override fun onSuccess() {
                pb?.visibility=View.GONE
            }

            override fun onError(e: Exception?) {
            }

        })
}

@BindingAdapter("android:url")
fun setWebView(wv:WebView, url:String){
    wv.webViewClient= WebViewClient()
    wv.loadUrl(url)
    wv.settings.javaScriptEnabled=true
}