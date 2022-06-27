package dk.ubaya.adv160819001finaltermproject.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentBookDetailBinding
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(),SwitchDarkClickListener {
    private lateinit var dataBinding:FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentSettingsBinding>(inflater,R.layout.fragment_settings,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setTitle("Settings")
        dataBinding.listener=this
    }

    override fun onSwitchDarkClick(v: View, mode:Boolean) {
        Log.e("cek",dataBinding.mode.toString())
        if(mode==true){
            Log.e("cek",dataBinding.mode.toString())
            dataBinding.mode=true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else {
            Log.e("cek",dataBinding.mode.toString())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}