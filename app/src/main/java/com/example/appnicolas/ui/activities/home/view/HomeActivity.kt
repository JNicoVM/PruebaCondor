package com.example.appnicolas.ui.activities.home.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnicolas.ui.activities.home.viewModel.HomeViewModel
import com.example.appnicolas.ui.adapters.TeamAdapter
import com.example.appnicolas.databinding.HomeActivityBinding
import com.example.appnicolas.utils.BurnedData
import dagger.hilt.android.AndroidEntryPoint
import android.widget.AdapterView


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
        homeViewModel.onCreate()
        initSpinner()
        initRecyclerView()
        listenerTeams()
        listenerFavorite()
        listenerIsLoading()
        listenerIsError()
    }

    //Init Spinner
    private fun initSpinner() {
        val aaLeague = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        aaLeague.addAll(BurnedData.appLeagueList.map { league -> league.name }.toList())
        binding.spLeague.adapter = aaLeague
        binding.spLeague.onItemSelectedListener = (object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                arg0: AdapterView<*>?, arg1: View,
                arg2: Int, arg3: Long
            ) {
                homeViewModel.cleanTeamsList()
                homeViewModel.getTeamsByLeague(BurnedData.appLeagueList[arg2].nameQuery)
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {

            }
        })
    }

    //Init recyclerView
    private fun initRecyclerView() {
        adapter = TeamAdapter(homeViewModel.localTeamList.toList(), homeViewModel.localfavoriteList.toList()){}
        binding.rvTeam.layoutManager = LinearLayoutManager(this)
        binding.rvTeam.adapter = adapter

    }

    //Filling the list of teams
    private fun listenerTeams() {
        homeViewModel.teamList.observe(this, Observer { teamList ->
            adapter = TeamAdapter(teamList, homeViewModel.localfavoriteList){teamItem->
                homeViewModel.insertFavorite(teamItem)
                homeViewModel.getFavorites()
            }
            adapter.notifyDataSetChanged()
        })
    }

    //listener favorite
    private fun listenerFavorite(){
        homeViewModel.favoriteList.observe(this, Observer { favoriteList ->
            adapter = TeamAdapter( homeViewModel.localTeamList, favoriteList){teamItem->
                homeViewModel.insertFavorite(teamItem)
                homeViewModel.getFavorites()
            }
            adapter.notifyDataSetChanged()
        })
    }


    //Loading screen
    private fun listenerIsLoading() {
        homeViewModel.isLoading.observe(this, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }

    //Error screen
    private fun listenerIsError() {
        homeViewModel.isError.observe(this, Observer { isError ->
            binding.tvError.visibility = if (isError) View.VISIBLE else View.GONE
        })
    }


}

