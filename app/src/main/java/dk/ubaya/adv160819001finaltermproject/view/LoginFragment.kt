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
import androidx.navigation.fragment.findNavController
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentLoginBinding
import dk.ubaya.adv160819001finaltermproject.model.User
import dk.ubaya.adv160819001finaltermproject.model.UserLogin
import dk.ubaya.adv160819001finaltermproject.viewmodel.UserLogViewModel
import dk.ubaya.adv160819001finaltermproject.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class LoginFragment : Fragment(),RegisterInLoginClickListener,LoginClickListener {
    private lateinit var viewModel:UserViewModel
    private lateinit var viewModelUserLog:UserLogViewModel
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
        (activity as AppCompatActivity).buttonLogout.visibility=View.GONE
        (activity as AppCompatActivity).bottomNav.visibility=View.INVISIBLE
        (activity as AppCompatActivity).supportActionBar?.setTitle("Login")
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModelUserLog=ViewModelProvider(this).get(UserLogViewModel::class.java)
        dataBinding.user= User("","","")
        dataBinding.registerlistener=this
        dataBinding.loginlistener=this
    }

    override fun onLoginClick(v: View, obj: User) {
        viewModel.check(obj)
        Log.e("aftercek",obj.toString())
        viewModel.userLD.observe(viewLifecycleOwner, Observer{
            Log.e("observe",it.toString())
            if (it.email==obj.email && it.password==obj.password){
                Log.e("elseif",viewModel.userLD.value.toString())
                viewModelUserLog.addLog(UserLogin(it.email))
                Navigation.findNavController(v).navigate(LoginFragmentDirections.actionLogin())
                Navigation.findNavController(v).graph.setStartDestination(findNavController().currentDestination!!.id)
            }else if(it==User("!@#$%^&*()_","!@#$%^&*()_","!@#$%^&*()_")){
                Toast.makeText(v.context,"Email or Password incorrect!", Toast.LENGTH_SHORT).show()
                dataBinding.user=User("","","")
                viewModel.userLD.value=User("","","")
            }
        })
    }

    override fun onRegisterInLoginClick(v: View) {
        Navigation.findNavController(v).navigate(LoginFragmentDirections.actionRegister())
    }
}