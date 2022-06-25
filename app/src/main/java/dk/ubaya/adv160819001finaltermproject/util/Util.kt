package dk.ubaya.adv160819001finaltermproject.util

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dk.ubaya.adv160819001finaltermproject.R
import java.lang.Exception

@BindingAdapter("imageUrl")
fun bindImage(view: ImageView,url:String){
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(view)
}