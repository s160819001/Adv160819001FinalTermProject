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
import androidx.recyclerview.widget.LinearLayoutManager
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentBookListBinding
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentThesisListBinding
import dk.ubaya.adv160819001finaltermproject.model.Thesis
import dk.ubaya.adv160819001finaltermproject.viewmodel.BookListViewModel
import dk.ubaya.adv160819001finaltermproject.viewmodel.ThesisListViewModel
import kotlinx.android.synthetic.main.fragment_thesis_list.*

class ThesisListFragment : Fragment(),AddThesisClickListener {
    private lateinit var viewModelThesis: ThesisListViewModel
    private lateinit var dataBinding: FragmentThesisListBinding
    private val thesisListAdapter2022  = ThesisListAdapter(arrayListOf())
    private val thesisListAdapter2021  = ThesisListAdapter(arrayListOf())
    private val thesisListAdapter2020  = ThesisListAdapter(arrayListOf())

    private val list2022: MutableList<Thesis> = arrayListOf()
    private val list2021: MutableList<Thesis> = arrayListOf()
    private val list2020: MutableList<Thesis> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentThesisListBinding>(inflater,R.layout.fragment_thesis_list,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setTitle("List of thesis")
        viewModelThesis = ViewModelProvider(this).get(ThesisListViewModel::class.java)
        viewModelThesis.refresh()
        recyclerView2022.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)
        recyclerView2022.adapter = thesisListAdapter2022

        recyclerView2021.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)
        recyclerView2021.adapter = thesisListAdapter2021

        recyclerView2020.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)
        recyclerView2020.adapter = thesisListAdapter2020

        refreshLayoutThesis.setOnRefreshListener {
            recyclerView2022.visibility = View.GONE
            txtErrorThesis2022.visibility = View.GONE
            progressLoadThesis2022.visibility = View.VISIBLE
            recyclerView2021.visibility = View.GONE
            txtErrorThesis2021.visibility = View.GONE
            progressLoadThesis2021.visibility = View.VISIBLE
            recyclerView2020.visibility = View.GONE
            txtErrorThesis2020.visibility = View.GONE
            progressLoadThesis2020.visibility = View.VISIBLE

            viewModelThesis.refresh()
            refreshLayoutThesis.isRefreshing = false
        }
        dataBinding.addThesislistener=this
        observeViewModel()
    }

    fun observeViewModel() {
        viewModelThesis.thesisLD.observe(viewLifecycleOwner, Observer {
            list2022.clear()
            list2021.clear()
            list2020.clear()
            for (i:Int in 0..(it.lastIndex)){
                if(it[i].year=="2022"){
                    list2022.add(Thesis(it[i].id,it[i].title,it[i].author,it[i].year))
                }else if(it[i].year=="2021"){
                    list2021.add(Thesis(it[i].id,it[i].title,it[i].author,it[i].year))
                }else if(it[i].year=="2020"){
                    list2020.add(Thesis(it[i].id,it[i].title,it[i].author,it[i].year))
                }
            }
            thesisListAdapter2022.updateThesisList(list2022)
            thesisListAdapter2021.updateThesisList(list2021)
            thesisListAdapter2020.updateThesisList(list2020)
        })
    }

    override fun onAddThesisClick(v: View) {
        Navigation.findNavController(v).navigate(ThesisListFragmentDirections.actionCreateThesis())
    }
}