package com.example.christopheraraujodimiour.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.christopheraraujodimiour.databinding.ProfileDetailsBinding
import com.example.christopheraraujodimiour.model.remote.Profile
import com.squareup.picasso.Picasso

class ProfileDetailFragment: Fragment() {
    companion object {
        fun newInstance(profile: Profile) =
            ProfileDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("Profile", profile)
                }
            }
    }

    private lateinit var binding: ProfileDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileDetailsBinding.inflate(
            inflater,
            container,
            false
        )
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        arguments?.getParcelable<Profile>("Profile")?.let { profile ->
            binding.apply {
                tvProfileName.text = profile.first_name
                Picasso.get().load(profile.avatar).into(ivProfileDetailAvatar)
            }
        }
    }
}