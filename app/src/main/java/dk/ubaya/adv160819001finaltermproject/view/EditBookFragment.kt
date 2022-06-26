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
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentEditBookBinding
import dk.ubaya.adv160819001finaltermproject.model.Book
import dk.ubaya.adv160819001finaltermproject.viewmodel.BookDetailViewModel
import kotlinx.android.synthetic.main.fragment_create_book.*


class EditBookFragment : Fragment(), BookSaveChangesListener {
    private lateinit var viewModel: BookDetailViewModel
    private lateinit var dataBinding: FragmentEditBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentEditBookBinding>(inflater,R.layout.fragment_edit_book,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setTitle("Edit Book")
        viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
        viewModel.fetch(EditBookFragmentArgs.fromBundle(requireArguments()).isbn)
        dataBinding.listener=this


//        btnAddBook.setOnClickListener {
//            var book= Book(inputISBN.text.toString(),inputTitle.text.toString(),inputAuthor.text.toString(),inputPublisher.text.toString(),inputYear.text.toString(),inputSynopsis.text.toString())
//            viewModel.addBook(book)
//            Toast.makeText(view.context, "Book has added to database.", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//        }

        viewModel.bookLD.observe(viewLifecycleOwner, Observer{
            dataBinding.book=it
        })
    }

    override fun onBookSaveChanges(v: View, obj: Book) {
        viewModel.update(
            obj.title,
            obj.author,
            obj.publisher,
            obj.year,
            obj.synopsis,
            obj.location.toString(),
            obj.image.toString(),
            obj.isbn)
        Toast.makeText(v.context, "Book data has updated.", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}