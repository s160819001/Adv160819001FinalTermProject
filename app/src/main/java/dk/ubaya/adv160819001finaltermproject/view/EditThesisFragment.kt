package dk.ubaya.adv160819001finaltermproject.view

import android.os.Bundle
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
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentEditThesisBinding
import dk.ubaya.adv160819001finaltermproject.model.Thesis
import dk.ubaya.adv160819001finaltermproject.viewmodel.BookDetailViewModel
import dk.ubaya.adv160819001finaltermproject.viewmodel.ThesisListViewModel

class EditThesisFragment : Fragment(),ThesisSaveChangesListener {
    private lateinit var viewModel:ThesisListViewModel
    private lateinit var dataBinding: FragmentEditThesisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentEditThesisBinding>(inflater,R.layout.fragment_edit_thesis,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setTitle("Edit Thesis")
        viewModel = ViewModelProvider(this).get(ThesisListViewModel::class.java)
        viewModel.fetch(EditThesisFragmentArgs.fromBundle(requireArguments()).id)
        dataBinding.listener=this

        viewModel.thesisLD.observe(viewLifecycleOwner, Observer{
            dataBinding.thesis=it[0]
        })
    }

    override fun onThesisSaveChanges(v: View, obj: Thesis) {
        viewModel.update(
            obj.title,
            obj.author,
            obj.year,
            obj.id)
        Toast.makeText(v.context, "Thesis data has updated.", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}