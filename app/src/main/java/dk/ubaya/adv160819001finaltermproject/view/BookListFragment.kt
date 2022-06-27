package dk.ubaya.adv160819001finaltermproject.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentBookDetailBinding
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentBookListBinding
import dk.ubaya.adv160819001finaltermproject.viewmodel.BookListViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*


class BookListFragment : Fragment(),AddBookClickListener,RefreshLayoutBookListener {
    private lateinit var viewModelBook: BookListViewModel
    private lateinit var dataBinding: FragmentBookListBinding
    private val bookListAdapter:BookListAdapter  = BookListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentBookListBinding>(inflater,R.layout.fragment_book_list,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setTitle("List of books")
        viewModelBook = ViewModelProvider(this).get(BookListViewModel::class.java)
        viewModelBook.refresh()

//        recView.layoutManager = LinearLayoutManager(context)
//        recView.adapter = bookListAdapter
        dataBinding.adapter=bookListAdapter
        dataBinding.listener=this
        dataBinding.refreshLayoutListener=this

        refreshLayoutBook.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModelBook.refresh()
            refreshLayoutBook.isRefreshing = false
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModelBook.booksLD.observe(viewLifecycleOwner, Observer {
            bookListAdapter.updateBookList(it)
        })

        viewModelBook.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            txtError.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModelBook.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                progressLoad.visibility = View.GONE
                recView.visibility = View.VISIBLE
            } else {
                progressLoad.visibility = View.VISIBLE
                recView.visibility = View.GONE
            }
        })
    }

    override fun onAddBookClick(v: View) {
        Navigation.findNavController(v).navigate(BookListFragmentDirections.actionCreateBook())
    }

    override fun onRefreshLayoutBook(v: View) {

    }
}