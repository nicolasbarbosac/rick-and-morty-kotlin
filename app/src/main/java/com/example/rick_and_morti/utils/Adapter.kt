package com.example.rick_and_morti.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rick_and_morti.R
import com.example.rick_and_morti.models.Results
import com.squareup.picasso.Picasso

class Adapter(private val mList: ArrayList<Results>, val clickListener: (Results) -> Unit) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.result_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.txtId.text = ItemsViewModel.id.toString()
        holder.txtName.text = ItemsViewModel.name
        holder.txtStatus.text = ItemsViewModel.status
        holder.txtSpecies.text = ItemsViewModel.species
        holder.txtType.text = ItemsViewModel.type
        holder.txtGender.text = ItemsViewModel.gender


        Picasso.with(holder.imgView.getContext()).load(ItemsViewModel.image).into(holder.imgView)
        (holder as ViewHolder).bind(mList[position],clickListener)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val txtId: TextView
        val txtName: TextView
        val txtStatus: TextView
        val txtSpecies: TextView
        val txtType: TextView
        val txtGender: TextView
        val imgView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            txtId = itemView.findViewById(R.id.txtId)
            txtName = itemView.findViewById(R.id.txtName)
            txtStatus = itemView.findViewById(R.id.txtStatus)
            txtSpecies = itemView.findViewById(R.id.txtSpecies)
            txtType = itemView.findViewById(R.id.txtType)
            txtGender = itemView.findViewById(R.id.txtGender)
            imgView = itemView.findViewById(R.id.imageView)
        }

        fun bind(part: Results, clickListener: (Results) -> Unit) {
            itemView.setOnClickListener { clickListener(part) }
        }

    }

}