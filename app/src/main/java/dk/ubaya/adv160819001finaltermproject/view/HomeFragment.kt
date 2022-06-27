package dk.ubaya.adv160819001finaltermproject.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentCreateBookBinding
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.activity_main.*

class HomeFragment : Fragment() {
    private lateinit var dataBinding:FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        (activity as AppCompatActivity).setSupportActionBar(toolbar)
//        val navHostFragment = (activity as FragmentActivity).supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        NavigationUI.setupActionBarWithNavController((activity as AppCompatActivity), navController, drawerLayout)
//        NavigationUI.setupWithNavController((activity as AppCompatActivity).navView, navController)
//        (activity as AppCompatActivity).bottomNav.setupWithNavController(navController)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentHomeBinding>(inflater,R.layout.fragment_home,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setTitle("Home")
        (activity as AppCompatActivity).bottomNav.visibility= View.VISIBLE
        (activity as AppCompatActivity).buttonLogout.visibility=View.VISIBLE
        dataBinding.mainimage="https://ubaya.fun/native/160819001/anmp/img/ubayalibrary.jpg"
        dataBinding.newsimage="https://ubaya.fun/native/160819001/anmp/img/news-1.jpg"
    }


}