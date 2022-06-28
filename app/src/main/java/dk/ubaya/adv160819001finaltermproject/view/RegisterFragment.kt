package dk.ubaya.adv160819001finaltermproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentCreateBookBinding
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentRegisterBinding
import dk.ubaya.adv160819001finaltermproject.model.User
import dk.ubaya.adv160819001finaltermproject.model.UserLogin
import dk.ubaya.adv160819001finaltermproject.viewmodel.BookDetailViewModel
import dk.ubaya.adv160819001finaltermproject.viewmodel.UserLogViewModel
import dk.ubaya.adv160819001finaltermproject.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class RegisterFragment : Fragment(),RegisterClickListener {
    private lateinit var viewModel:UserViewModel
    private lateinit var viewModelUserLog: UserLogViewModel
    private lateinit var dataBinding:FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentRegisterBinding>(inflater,R.layout.fragment_register,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setTitle("Register")
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModelUserLog=ViewModelProvider(this).get(UserLogViewModel::class.java)
        dataBinding.user= User("","","")
//        bottomNav.menu.getItem(R.id.itemProfile).title="Register"
        dataBinding.listener=this
    }

    override fun onRegisterClick(v: View, obj: User) {
        viewModel.addUser(obj)
        viewModelUserLog.addLog(UserLogin(obj.email))
        Navigation.findNavController(v).navigate(RegisterFragmentDirections.actionRegister())
    }
}