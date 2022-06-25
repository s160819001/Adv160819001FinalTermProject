package dk.ubaya.adv160819001finaltermproject.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.viewmodel.BookDetailViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*

class BookDetailFragment : Fragment() {
    private lateinit var viewModel: BookDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setTitle("Book Detail")
        viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
        viewModel.fetch(BookDetailFragmentArgs.fromBundle(requireArguments()).isbn)
        Log.e("isbn dikirim",BookDetailFragmentArgs.fromBundle(requireArguments()).isbn)

        viewModel.bookLD.observe(viewLifecycleOwner, Observer{
            txtTitleDetail.setText(it.title)
            txtAuthorDetail.setText(it.author)
            txtPublisherDetail.setText(it.publisher)
            txtYear.setText(it.year)
            txtISBN.setText(it.isbn)
            txtSynopsis.setText(it.synopsis)
//            imageViewDetail.loadImage(it.image, progressBarDetail)

            var book=it
            btnFindBook.setOnClickListener {
//                Navigation.findNavController(it).navigate(BookDetailFragmentDirections.actionLocationFragment(book.location.toString()))
            }
        })
    }
}