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
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentEditProfileBinding
import dk.ubaya.adv160819001finaltermproject.model.User
import dk.ubaya.adv160819001finaltermproject.viewmodel.BookDetailViewModel
import dk.ubaya.adv160819001finaltermproject.viewmodel.UserViewModel

class EditProfileFragment : Fragment(),UserSaveChangesListener {
    private lateinit var viewModel: UserViewModel
    private lateinit var dataBinding:FragmentEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentEditProfileBinding>(inflater,R.layout.fragment_edit_profile,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setTitle("Edit Profile")
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.fetch(EditProfileFragmentArgs.fromBundle(requireArguments()).email)
        viewModel.userLD.observe(viewLifecycleOwner, Observer{
            dataBinding.user=it
        })
        dataBinding.listener=this
    }

    override fun onUserSaveChanges(v: View, obj: User) {
        viewModel.update(
            obj.name,
            obj.email,
            obj.password)
        Toast.makeText(v.context, "Profile data has updated.", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}