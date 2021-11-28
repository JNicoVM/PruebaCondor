package com.example.appnicolas.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.appnicolas.R
import com.example.appnicolas.data.entities.Favorite
import com.example.appnicolas.databinding.TeamContainerBinding
import com.example.appnicolas.data.model.response.TeamResponse
import com.example.appnicolas.ui.activities.detail.view.DetailActivity
import com.squareup.picasso.Picasso

class TeamAdapter(private val teams:List<TeamResponse>, private val favorites : List<Favorite>,private val onFavorite: (TeamResponse) -> Unit): RecyclerView.Adapter<TeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TeamViewHolder(layoutInflater.inflate(R.layout.team_container, parent, false))
    }

    override fun getItemCount(): Int  = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
     val item = teams[position]
        holder.bind(item,favorites, onFavorite)
    }

}

//TeamViewHolder
class TeamViewHolder(val view: View): RecyclerView.ViewHolder(view){

    // binding
    private val binding = TeamContainerBinding.bind(view)

    fun bind(team:TeamResponse?,  favorites : List<Favorite>, onFavorite: (TeamResponse) -> Unit){
        binding.tvName.text = team?.name ?: "No data found"
        team?.imgBadge.let { img ->
            Picasso.get().load(img).into(binding.ivBadge)
        }
        team?.id.let { teamId->
            favorites.let {  favoriteList->
                if(favoriteList.map { e->e.teamId }.toList().contains(teamId)){
                    binding.favouritebtn.setBackgroundResource(R.drawable.start_yellow)
                    binding.favouritebtn.isClickable = false
                }else{
                    binding.favouritebtn.setBackgroundResource(R.drawable.start_white)
                    binding.favouritebtn.setOnClickListener {
                        team?.let { teamItem->
                            onFavorite(teamItem)
                        }
                    }
                    }

            }
        }
        binding.viewRoot.setOnClickListener {
             val intent =  Intent(view.context, DetailActivity::class.java)
            intent.putExtra("teamName", team?.name)
            intent.putExtra("teamDescription", team?.description)
            intent.putExtra("teamFoundation", team?.foundationYear)
            intent.putExtra("imgBadge", team?.imgBadge)
            intent.putExtra("imgShirt", team?.imgShirt)
            intent.putExtra("teamId", team?.id)
            intent.putExtra("facebookUrl", team?.facebookUrl)
            intent.putExtra("twitterUrl", team?.twitterUrl)
            intent.putExtra("instagramUrl", team?.instagramUrl)
            intent.putExtra("youtubeUrl", team?.youtubeUrl)
            intent.putExtra("website", team?.website)
            startActivity(view.context, intent,null)
        }
    }
}
