package com.example.appnicolas.ui.activities.detail.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnicolas.databinding.DetailActivityBinding
import com.example.appnicolas.ui.activities.detail.viewModel.DetailViewModel
import com.example.appnicolas.ui.adapters.EventAdapter
import com.example.appnicolas.ui.adapters.TeamAdapter
import com.example.appnicolas.utils.Validator
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    //Lateinit of binding
    lateinit var binding: DetailActivityBinding

    //Init event adapter
    private lateinit var adapter: EventAdapter

    private lateinit var bundle: Bundle

    //ViewModel
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        loadData()
        onClickBackButton()
        onClickFacebookImage()
        onClickTwitterImage()
        onClickWebImage()
        onClickYoutubeImage()
        onClickInstagramImage()
        listenerTeams()
        listenerIsLoading()
        listenerIsError()
        detailViewModel.onCreate(bundle.getString("teamId") ?: "")
    }

    //Init recyclerView
    private fun initRecyclerView() {
        adapter = EventAdapter(detailViewModel.localEventList.toList())
        binding.rvEvent.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvEvent.adapter = adapter

    }

    //Load data
    private fun loadData() {
        bundle = intent.extras ?: Bundle()
        bundle.let {
            bundle.apply {
                binding.tvNameContent.text = getString("teamName")
                binding.tvDescriptionContent.text = getString("teamDescription")
                binding.tvFoundationContent.text = getString("teamFoundation")
                getString("imgBadge")?.let { img ->
                    Picasso.get().load(img).into(binding.ivBadge)
                }
                getString("imgShirt").let { img ->
                    Picasso.get().load(img).into(binding.ivShirt)
                }
            }
        }
    }

    //Filling the list of events
    private fun listenerTeams() {
        detailViewModel.eventList.observe(this, Observer { eventList ->
            adapter = EventAdapter(eventList)
            binding.rvEvent.adapter = adapter
        })
    }

    //Loading screen
    private fun listenerIsLoading() {
        detailViewModel.isLoading.observe(this, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }

    //Error screen
    private fun listenerIsError() {
        detailViewModel.isError.observe(this, Observer { isError ->
            binding.tvError.visibility = if (isError) View.VISIBLE else View.GONE
        })
    }

    //listener onClick backButton
    private fun onClickBackButton() {
        binding.customBackButton.setOnClickListener {
            this.onBackPressed()
        }
    }

    //listener onClick twitter
    private fun onClickTwitterImage() {
        val urlString = Validator.buildValidUrl(bundle.getString("twitterUrl") ?: "")
        binding.ivTwitter.visibility = if (Validator.isValidUrl(urlString)) View.VISIBLE else View.GONE
        binding.ivTwitter.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlString)))
        }
    }

    //listener onClick youtube
    private fun onClickYoutubeImage() {
        val urlString = Validator.buildValidUrl(bundle.getString("youtubeUrl") ?: "")
        binding.ivYoutube.visibility =if (Validator.isValidUrl(urlString)) View.VISIBLE else View.GONE
        binding.ivYoutube.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlString)))
        }
    }

    //listener onClick facebook
    private fun onClickFacebookImage() {
        val urlString = Validator.buildValidUrl(bundle.getString("facebookUrl")?:"")
        binding.ivFacebook.visibility = if (Validator.isValidUrl(urlString)) View.VISIBLE else View.GONE
        binding.ivFacebook.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlString)))
        }
    }

    //listener onClick instagram
    private fun onClickInstagramImage() {
        val urlString = Validator.buildValidUrl(bundle.getString("instagramUrl")?:"")
        binding.ivInstagram.visibility = if (Validator.isValidUrl(urlString)) View.VISIBLE else View.GONE
        binding.ivInstagram.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlString)))
        }
    }

    //listener onClick web
    private fun onClickWebImage() {
        val urlString = Validator.buildValidUrl(bundle.getString("website")?:"")
        binding.ivWWW.visibility =if (Validator.isValidUrl(urlString)) View.VISIBLE else View.GONE
        binding.ivWWW.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlString)))
        }
    }
}