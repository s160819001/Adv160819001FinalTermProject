package dk.ubaya.adv160819001finaltermproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentLoginBinding
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentRegisterBinding
import dk.ubaya.adv160819001finaltermproject.model.User
import dk.ubaya.adv160819001finaltermproject.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class LoginFragment : Fragment(),RegisterInLoginClickListener,LoginClickListener {
    private lateinit var viewModel:UserViewModel
   private lateinit var dataBinding:FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentLoginBinding>(inflater,R.layout.fragment_login,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setTitle("Login")
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//        bottomNav.menu.getItem(R.id.itemProfile).title="Login"
        dataBinding.user= User("","","")
        dataBinding.registerlistener=this
        dataBinding.loginlistener=this
    }

    override fun onLoginClick(v: View, obj: User) {
        Navigation.findNavController(v).navigate(LoginFragmentDirections.actionLogin(obj.email,obj.password))
    }

    override fun onRegisterInLoginClick(v: View) {
        Navigation.findNavController(v).navigate(LoginFragmentDirections.actionRegister())
    }
}