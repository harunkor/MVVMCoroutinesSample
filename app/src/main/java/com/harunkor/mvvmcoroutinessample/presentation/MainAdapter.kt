package com.harunkor.mvvmcoroutinessample.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.harunkor.mvvmcoroutinessample.R
import com.harunkor.mvvmcoroutinessample.domain.model.User
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MainAdapter @Inject constructor() : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    private var users: ArrayList<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainAdapter.DataViewHolder, position: Int) {
       holder.bind(users.get(position))
    }

    override fun getItemCount(): Int = users.size

    class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(user: User) {
           val username: TextView = itemView.findViewById(R.id.textViewUserName)
           val mail: TextView = itemView.findViewById(R.id.textViewUserEmail)
           val avatar: ImageView = itemView.findViewById(R.id.imageViewAvatar)
           username.text=user.name
           mail.text=user.email
           Picasso.get().load(user.avatar).error(R.drawable.placeholder_image).into(avatar)
        }
    }
    fun addData(users: List<User>){
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}