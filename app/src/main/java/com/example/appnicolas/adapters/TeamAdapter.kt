package com.example.appnicolas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appnicolas.R
import com.example.appnicolas.databinding.TeamContainerBinding
import com.example.appnicolas.data.model.response.TeamResponse
import com.squareup.picasso.Picasso

class TeamAdapter(private val teams:List<TeamResponse>): RecyclerView.Adapter<TeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TeamViewHolder(layoutInflater.inflate(R.layout.team_container, parent, false))
    }

    override fun getItemCount(): Int  = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
     val item = teams[position]
        holder.bind(item.imgBadge, item.name)
    }

}

//TeamViewHolder
class TeamViewHolder(view: View): RecyclerView.ViewHolder(view){

    //lateinit binding
    private val binding = TeamContainerBinding.bind(view)

    fun bind(image:String?, name:String?){
        binding.tvName.text = name ?: "No data found"
        image.let { img ->
            Picasso.get().load(img).into(binding.ivBadge)
        }

    }
}
