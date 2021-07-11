package com.example.weatherapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.Location
import com.example.weatherapp.view.DetailsActivity
import kotlinx.android.synthetic.main.rv_location_child.view.*

class LocationAdapter(private val context : Context) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {
        private var list : List<Location> = ArrayList()
//This function for the we don't want to create the adapter again and again ,
// This function keeps update its value
    fun setlocationlist(list : List<Location>){
        this.list = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.rv_location_child, parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationAdapter.ViewHolder, position: Int) {
        holder.name.text = list[position].title
        holder.latLng.text = list[position].latt_long
        holder.rootView.setOnClickListener {
            val intent = Intent(context,DetailsActivity::class.java)
            intent.putExtra("Location", list[position].woeid)
            intent.putExtra("name", list[position].title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val name = v.tv_location_name!!
        val latLng = v.tv_lat_lng!!
        val rootView = v.child_root!!
    }
}