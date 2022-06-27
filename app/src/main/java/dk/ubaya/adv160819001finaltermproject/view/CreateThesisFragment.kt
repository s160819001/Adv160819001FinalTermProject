package dk.ubaya.adv160819001finaltermproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentCreateBookBinding
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentCreateThesisBinding
import dk.ubaya.adv160819001finaltermproject.model.Book
import dk.ubaya.adv160819001finaltermproject.model.Thesis
import dk.ubaya.adv160819001finaltermproject.viewmodel.BookDetailViewModel
import dk.ubaya.adv160819001finaltermproject.viewmodel.ThesisListViewModel

class CreateThesisFragment : Fragment(), CreateThesisClickListener{
    private lateinit var viewModel: ThesisListViewModel
    private lateinit var dataBinding: FragmentCreateThesisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentCreateThesisBinding>(inflater,R.layout.fragment_create_thesis,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setTitle("Add Thesis")
        viewModel = ViewModelProvider(this).get(ThesisListViewModel::class.java)
        dataBinding.listener=this

        dataBinding.thesis= Thesis("","","","")
    }

    override fun onCreateThesisClick(v: View, obj: Thesis) {
        viewModel.addThesis(obj)
        Toast.makeText(v.context, "Thesis has added to database.", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}