package dk.ubaya.adv160819001finaltermproject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.databinding.BookListItemBinding
import dk.ubaya.adv160819001finaltermproject.databinding.ThesisListItemBinding
import dk.ubaya.adv160819001finaltermproject.model.Thesis

class ThesisListAdapter(val thesisList:ArrayList<Thesis>): RecyclerView.Adapter<ThesisListAdapter.ThesisViewHolder>(),ThesisCardViewClickListener {
    class ThesisViewHolder(var view: ThesisListItemBinding) : RecyclerView.ViewHolder(view.root)

    fun updateThesisList(newThesisList: List<Thesis>) {
        thesisList.clear()
        thesisList.addAll(newThesisList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThesisViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v= DataBindingUtil.inflate<ThesisListItemBinding>(inflater,R.layout.thesis_list_item,parent,false)
        return ThesisViewHolder(v)
    }

    override fun onBindViewHolder(holder: ThesisViewHolder, position: Int) {
        holder.view.thesis=thesisList[position]
        holder.view.listener=this
    }

    override fun getItemCount(): Int {
        return thesisList.size
    }

    override fun onThesisCardViewClick(v: View, obj: Thesis) {
        Navigation.findNavController(v).navigate(ThesisListFragmentDirections.actionThesisDetail(obj.id,obj.year))
    }

}