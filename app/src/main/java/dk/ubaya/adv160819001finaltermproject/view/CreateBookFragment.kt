package dk.ubaya.adv160819001finaltermproject.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.model.Book
import dk.ubaya.adv160819001finaltermproject.viewmodel.BookDetailViewModel
import kotlinx.android.synthetic.main.fragment_create_book.*


class CreateBookFragment : Fragment() {
    private lateinit var viewModel: BookDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setTitle("Add Book")
        viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)

        btnAddBook.setOnClickListener {
            Log.e("masuk","btnaddbook masuk sini")
            var book= Book(inputISBN.text.toString(),inputTitle.text.toString(),inputAuthor.text.toString(),inputPublisher.text.toString(),inputYear.text.toString(),inputSynopsis.text.toString())
            Log.e("book",book.toString())
            viewModel.addBook(book)
            Toast.makeText(view.context, "Book has added to database.", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }


//        viewModel.fetch(BookDetailFragmentArgs.fromBundle(requireArguments()).isbn.toString())

        viewModel.bookLD.observe(viewLifecycleOwner, Observer{


        })
    }
}