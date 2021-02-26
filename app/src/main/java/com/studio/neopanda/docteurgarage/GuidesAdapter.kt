package com.studio.neopanda.docteurgarage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.guide_list_item.view.*

class GuidesAdapter(private val guides: ArrayList<Guide>, private val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return guides.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.guide_list_item,
                parent,
                false
            )
        )
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvGuidesTitle.text = guides[position].title
        holder.tvGuidesDate.text = guides[position].date
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvGuidesTitle = view.tv_guide_type!!
    val tvGuidesDate = view.tv_guide_date!!
}