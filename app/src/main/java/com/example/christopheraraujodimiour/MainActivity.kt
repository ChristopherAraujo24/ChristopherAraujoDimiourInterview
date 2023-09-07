package com.example.christopheraraujodimiour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import com.example.christopheraraujodimiour.databinding.ActivityMainBinding
import com.example.christopheraraujodimiour.model.remote.DataResponse
import com.example.christopheraraujodimiour.model.remote.PresentationData
import com.example.christopheraraujodimiour.model.remote.Profile
import com.example.christopheraraujodimiour.view.adapters.ProfileAdapter
import com.example.christopheraraujodimiour.view.fragment.ProfileDetailFragment
import com.example.christopheraraujodimiour.view.fragment.ProfileListFragment
import com.example.christopheraraujodimiour.viewmodel.ProfileViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ProfileListFragment())
            .commit()
    }

     fun isLoading() {
        binding.loading.visibility = if (binding.loading.showShow()) View.GONE else View.VISIBLE
    }

     fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }


}

private fun View.showShow(): Boolean {
    return (this.visibility == View.VISIBLE)
}

// so display a list of items
// having a mvvm
// fetch data from api
//https://reqres.in/api/users?page=2