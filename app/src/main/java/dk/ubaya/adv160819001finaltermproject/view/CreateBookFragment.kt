package dk.ubaya.adv160819001finaltermproject.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentCreateBookBinding
import dk.ubaya.adv160819001finaltermproject.model.Book
import dk.ubaya.adv160819001finaltermproject.viewmodel.BookDetailViewModel
import kotlinx.android.synthetic.main.fragment_create_book.*


class CreateBookFragment : Fragment(), CreateBookClickListener{
    private lateinit var viewModel: BookDetailViewModel
    private lateinit var dataBinding:FragmentCreateBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentCreateBookBinding>(inflater,R.layout.fragment_create_book,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setTitle("Add Book")
        viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)

        dataBinding.listener=this

        dataBinding.book= Book("","","","","","","")
    }

    override fun onCreateBookClick(v: View, obj: Book) {
        viewModel.addBook(obj)
        Toast.makeText(v.context, "Book has added to database.", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}