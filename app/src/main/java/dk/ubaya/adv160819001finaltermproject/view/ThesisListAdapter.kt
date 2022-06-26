package dk.ubaya.adv160819001finaltermproject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.model.Thesis

class ThesisListAdapter(val thesisList:ArrayList<Thesis>): RecyclerView.Adapter<ThesisListAdapter.ThesisViewHolder>() {
    class ThesisViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    fun updateThesisList(newThesisList: List<Thesis>) {
        thesisList.clear()
        thesisList.addAll(newThesisList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThesisViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.thesis_list_item, parent, false)
        return ThesisViewHolder(v)
    }

    override fun onBindViewHolder(holder: ThesisViewHolder, position: Int) {
//        holder.view.txtTitleThesis.text = thesisList[position].title
//        holder.view.txtAuthorThesis.text = thesisList[position].author
//        holder.view.txtYearThesis.text = thesisList[position].year
//
//        holder.view.cardViewThesis.setOnClickListener {
//            Navigation.findNavController(it).navigate(ThesisListFragmentDirections.actionThesisDetail(thesisList[position].id.toString(),thesisList[position].year.toString()))
        }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}