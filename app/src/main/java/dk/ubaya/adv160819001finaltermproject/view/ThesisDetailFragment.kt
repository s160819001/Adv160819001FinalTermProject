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
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentBookDetailBinding
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentThesisDetailBinding
import dk.ubaya.adv160819001finaltermproject.model.Thesis
import dk.ubaya.adv160819001finaltermproject.viewmodel.BookDetailViewModel
import dk.ubaya.adv160819001finaltermproject.viewmodel.ThesisListViewModel

class ThesisDetailFragment : Fragment(),EditThesisClickListener,DeleteThesisClickListener {
    private lateinit var dataBinding: FragmentThesisDetailBinding
    private lateinit var viewModel: ThesisListViewModel

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentThesisDetailBinding>(inflater,R.layout.fragment_thesis_detail,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setTitle("Thesis Abstraction")

        var id=ThesisDetailFragmentArgs.fromBundle(requireArguments()).id
        var year=ThesisDetailFragmentArgs.fromBundle(requireArguments()).year

        viewModel = ViewModelProvider(this).get(ThesisListViewModel::class.java)
        viewModel.fetch(id)

        val splitid = id.split("-")
        val useid = splitid[1]+"_"+splitid[2]
        if(year=="2022"){
            year="file3"
        }else if(year=="2021"||year=="2020")
            year="file2"
        val url= "https://digilib.ubaya.ac.id/index.php?page=view/pdf_list&kode=$id&file=uploads_pdfmirrorghost/$year/$id/"+useid+"_Abstrak.pdf"

        dataBinding.url=url
        dataBinding.editListener=this
        dataBinding.deleteListener=this
        viewModel.thesisLD.observe(viewLifecycleOwner, Observer{
            dataBinding.thesis=it[0]
        })
    }

    override fun onEditThesisClick(v: View, obj: Thesis) {
        Navigation.findNavController(v).navigate(ThesisDetailFragmentDirections.actioneEditThesis(obj.id))
    }

    override fun onDeleteThesisClick(v: View, obj: Thesis) {
        viewModel.delete(obj)
        Toast.makeText(v.context, "Thesis has deleted.", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}