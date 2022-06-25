package dk.ubaya.adv160819001finaltermproject.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethods
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.databinding.BookListItemBinding
import dk.ubaya.adv160819001finaltermproject.model.Book
import kotlinx.android.synthetic.main.book_list_item.view.*
import okhttp3.internal.Util

class BookListAdapter (val bookList:ArrayList<Book>): RecyclerView.Adapter<BookListAdapter.BookViewHolder>(),CardViewClickListener {
    class BookViewHolder(var view: BookListItemBinding) : RecyclerView.ViewHolder(view.root)

    fun updateBookList(newBookList: List<Book>) {
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val v = inflater.inflate(R.layout.book_list_item, parent, false)
        val v=DataBindingUtil.inflate<BookListItemBinding>(inflater,R.layout.book_list_item,parent,false)
        return BookViewHolder(v)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.view.book = bookList[position]
        holder.view.listener = this
//        holder.view.txtTitle.text = bookList[position].title
//        holder.view.txtAuthor.text = bookList[position].author
//        holder.view.txtPublisher.text = bookList[position].publisher
//        holder.view.imageView.loadImage(bookList[position].image.toString(), holder.view.progressBar)
//
//        holder.view.cardView.setOnClickListener {
////            Navigation.findNavController(it).navigate(BookListFragmentDirections.actionBookDetail(bookList[position].isbn.toString()))
//        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onCardViewClick(v: View) {
        Log.e("cek tag value",v.tag.toString())
        Navigation.findNavController(v).navigate(BookListFragmentDirections.actionBookDetail(v.tag.toString()))
    }

}