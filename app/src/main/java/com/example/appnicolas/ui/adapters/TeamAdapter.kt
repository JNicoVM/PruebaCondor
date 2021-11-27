package com.example.appnicolas.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.appnicolas.R
import com.example.appnicolas.databinding.TeamContainerBinding
import com.example.appnicolas.data.model.response.TeamResponse
import com.example.appnicolas.ui.activities.detail.view.DetailActivity
import com.squareup.picasso.Picasso

class TeamAdapter(private val teams:List<TeamResponse>): RecyclerView.Adapter<TeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TeamViewHolder(layoutInflater.inflate(R.layout.team_container, parent, false))
    }

    override fun getItemCount(): Int  = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
     val item = teams[position]
        holder.bind(item)
    }

}

//TeamViewHolder
class TeamViewHolder(val view: View): RecyclerView.ViewHolder(view){

    //lateinit binding
    private val binding = TeamContainerBinding.bind(view)

    fun bind(team:TeamResponse?){
        binding.tvName.text = team?.name ?: "No data found"
        team?.imgBadge.let { img ->
            Picasso.get().load(img).into(binding.ivBadge)
        }
        binding.viewRoot.setOnClickListener {
             val intent =  Intent(view.context, DetailActivity::class.java)
            intent.putExtra("teamName", team?.name)
            intent.putExtra("teamDescription", team?.description)
            intent.putExtra("teamFoundation", team?.foundationYear)
            intent.putExtra("imgBadge", team?.imgBadge)
            intent.putExtra("imgShirt", team?.imgShirt)
            startActivity(view.context, intent,null)
        }
    }
}
