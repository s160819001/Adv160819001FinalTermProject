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
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentBookDetailBinding
import dk.ubaya.adv160819001finaltermproject.model.Book
import dk.ubaya.adv160819001finaltermproject.viewmodel.BookDetailViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*

class BookDetailFragment : Fragment(),EditBookClickListener, MoreBookClickListener{
    private lateinit var viewModel: BookDetailViewModel
    private lateinit var dataBinding:FragmentBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding=DataBindingUtil.inflate<FragmentBookDetailBinding>(inflater,R.layout.fragment_book_detail,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //untuk membantu agar tidak null, dan nantinya akan direplace dengan data dari viewmodel
        dataBinding.book= Book("","","","","","","")
        (activity as AppCompatActivity).supportActionBar?.setTitle("Book Detail")
        viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
        viewModel.fetch(BookDetailFragmentArgs.fromBundle(requireArguments()).isbn)
        observeViewModel()
        dataBinding.editListener=this
        dataBinding.listener=this
    }

    override fun onPause() {
        super.onPause()
        //juga untuk membantu ketika masuk edit, setelah save changes data tidak berubah karena observenya tidak jalan
        dataBinding.book= Book("","","","","","","")
    }

    override fun onEditBookClick(v: View, obj: Book) {
        Navigation.findNavController(v).navigate(BookDetailFragmentDirections.actionEditBook(obj.isbn))
    }

    fun observeViewModel(){
        viewModel.bookLD.observe(viewLifecycleOwner, Observer{
            dataBinding.book=it
        })
    }

    override fun onMoreBookClick(v: View) {
        Log.e("masuk","masukonmorebookclick")
        Navigation.findNavController(v).navigate(BookDetailFragmentDirections.actionMore(v.tag.toString()))
    }
}