package dk.ubaya.adv160819001finaltermproject.view

import android.os.Bundle
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
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentProfileBinding
import dk.ubaya.adv160819001finaltermproject.model.User
import dk.ubaya.adv160819001finaltermproject.viewmodel.UserLogViewModel
import dk.ubaya.adv160819001finaltermproject.viewmodel.UserViewModel

class ProfileFragment : Fragment(),EditUserClickListener {
    private lateinit var viewModel: UserViewModel
    private lateinit var viewModelUserLog:UserLogViewModel
    private lateinit var dataBinding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentProfileBinding>(inflater,R.layout.fragment_profile,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setTitle("Profile")
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModelUserLog = ViewModelProvider(this).get(UserLogViewModel::class.java)
//        viewModel.fetch(User(ProfileFragmentArgs.fromBundle(requireArguments()).email,"",ProfileFragmentArgs.fromBundle(requireArguments()).password))
//        viewModel.fetch()
        viewModelUserLog.fetch()

        dataBinding.listener=this

        viewModelUserLog.userLogLD.observe(viewLifecycleOwner, Observer{
            viewModel.fetch(it.email)
        })

        viewModel.userLD.observe(viewLifecycleOwner, Observer{
            dataBinding.user=it
        })

    }

    override fun onPause() {
        super.onPause()
        dataBinding.user=User("","","")
    }
    override fun onEditUserClick(v: View, obj: User) {
        Navigation.findNavController(v).navigate(ProfileFragmentDirections.actionEditProfile(obj.email))
    }
}