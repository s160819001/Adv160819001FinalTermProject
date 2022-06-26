package dk.ubaya.adv160819001finaltermproject.view

import android.util.Log
import android.view.View
import dk.ubaya.adv160819001finaltermproject.model.Book

interface CardViewClickListener{
    fun onCardViewClick(v:View)
}

interface CreateBookClickListener{
    fun onCreateBookClick(v: View, obj:Book){
        Log.e("tes", "oncreatebookclick dipanggil")
        Log.e("tes",obj.toString())
    }
}

interface BookSaveChangesListener{
    fun onBookSaveChanges(v: View, obj:Book)
}

interface EditBookClickListener{
    fun onEditBookClick(v:View,obj: Book)
}

interface FindBookClickListener{
    fun onFindBookClick(v: View,obj: Book)
}