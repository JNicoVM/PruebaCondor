package com.example.appnicolas.ui.activities.home.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnicolas.ui.activities.home.viewModel.HomeViewModel
import com.example.appnicolas.ui.adapters.TeamAdapter
import com.example.appnicolas.databinding.HomeActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    //Late binding
    private lateinit var binding: HomeActivityBinding
    //Init TeamAdapter
    private lateinit var adapter: TeamAdapter

    //ViewModel
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        listenerTeams()
        listenerIsLoading()
        listenerIsError()
        homeViewModel.onCreate()

    }

    //Init recyclerView
    private fun initRecyclerView() {
        adapter = TeamAdapter(homeViewModel.localTeamList.toList())
        binding.rvTeam.layoutManager = LinearLayoutManager(this)
        binding.rvTeam.adapter = adapter

    }

    //Filling the list of teams
    private fun listenerTeams() {
        homeViewModel.teamList.observe(this, Observer { teamList ->
            adapter = TeamAdapter(teamList)
            binding.rvTeam.adapter = adapter
        })
    }

    //Loading screen
    private fun listenerIsLoading(){
        homeViewModel.isLoading.observe(this, Observer { isLoading->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }

    //Error screen
    private fun listenerIsError(){
        homeViewModel.isError.observe(this, Observer { isError->
            binding.tvError.visibility = if (isError) View.VISIBLE else View.GONE
        })
    }


}

