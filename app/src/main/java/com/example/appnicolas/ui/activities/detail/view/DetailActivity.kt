package com.example.appnicolas.ui.activities.detail.view

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.appnicolas.databinding.DetailActivityBinding
import com.squareup.picasso.Picasso

class DetailActivity :AppCompatActivity(){

    //Lateinit of binding
    lateinit var binding: DetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        onClickBackButton()
    }

    //Load data
    private fun loadData(){
        val bundle = intent.extras
        bundle?.let {
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

    //listener onClick backButton
    private fun onClickBackButton(){
        binding.customBackButton.setOnClickListener {
            this.onBackPressed()
        }
    }
}