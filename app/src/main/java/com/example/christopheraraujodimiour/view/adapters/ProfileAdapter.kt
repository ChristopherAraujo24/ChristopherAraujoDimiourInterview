package com.example.christopheraraujodimiour.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.christopheraraujodimiour.databinding.ProfileItemLayoutBinding
import com.example.christopheraraujodimiour.model.remote.Profile
import com.squareup.picasso.Picasso

class ProfileAdapter(private var dataSet: List<Profile>, private val openDetails: (Profile) -> Unit): RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    class ProfileViewHolder(private val binding: ProfileItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(profileItem: Profile, openDetails: (Profile) -> Unit) {
            binding.apply {
                tvProfileFn.text = profileItem.first_name
                tvProfileLn.text = profileItem.last_name
                tvProfileEmail.text = profileItem.email
                Picasso.get().load(profileItem.avatar).into(ivProfileAvatar)
                root.setOnClickListener {
                    openDetails(profileItem)
                }
            }
        }
    }

    fun updateDataset(newList: List<Profile>) {
        dataSet = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(
            ProfileItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(
            dataSet[position],
            openDetails
        )
    }
}