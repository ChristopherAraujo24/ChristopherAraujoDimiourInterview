package com.example.christopheraraujodimiour.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.christopheraraujodimiour.MainActivity
import com.example.christopheraraujodimiour.R
import com.example.christopheraraujodimiour.databinding.ProfileListFragmentBinding
import com.example.christopheraraujodimiour.model.remote.DataResponse
import com.example.christopheraraujodimiour.model.remote.PresentationData
import com.example.christopheraraujodimiour.model.remote.Profile
import com.example.christopheraraujodimiour.view.adapters.ProfileAdapter
import com.example.christopheraraujodimiour.viewmodel.ProfileViewModel

class ProfileListFragment : Fragment() {
    private lateinit var binding: ProfileListFragmentBinding
    private val profileViewModel: ProfileViewModel by lazy {
        ViewModelProvider(this)[ProfileViewModel::class.java]
    }
    private val adapter: ProfileAdapter by lazy {
        ProfileAdapter(emptyList()) {
            openFragmentDetails(it)
        }
    }
    private fun openFragmentDetails(profileDAta: Profile) {
        activity?.navigateDetails(profileDAta)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileListFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        setupUI()
        setupObservers()
        return binding.root
    }

    private fun setupObservers() {
        profileViewModel.profileList.observe(viewLifecycleOwner) { presentationData ->
            when (presentationData) {
                is PresentationData.Success -> { updateData(presentationData.data) }
                is PresentationData.Error -> { showError(presentationData.error) }
                PresentationData.Loading -> { isLoading() }
            }
        }
    }

    private fun isLoading() {
        (activity as MainActivity).isLoading()
    }

    private fun showError(error: String) {
        (activity as MainActivity).showError(error)
    }

    private fun setupUI() {
        binding.profileList.adapter = adapter
        binding.profileList.layoutManager = LinearLayoutManager(context)
    }

    private fun updateData(newList: DataResponse) {
        adapter.updateDataset(newList.data)
    }

    private fun FragmentActivity.navigateDetails(profile: Profile) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,
                ProfileDetailFragment.newInstance(profile))
            .addToBackStack(null)
            .commit()
    }
}