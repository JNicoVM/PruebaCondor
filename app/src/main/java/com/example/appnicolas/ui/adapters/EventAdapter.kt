package com.example.appnicolas.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appnicolas.R
import com.example.appnicolas.data.model.response.EventResponse
import com.example.appnicolas.databinding.EventContainerBinding

class EventAdapter(private val events:List<EventResponse>): RecyclerView.Adapter<EventViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EventViewHolder(layoutInflater.inflate(R.layout.event_container, parent, false))
    }

    override fun getItemCount(): Int  = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
     val item = events[position]
        holder.bind(item)
    }

}

//EventViewHolder
class EventViewHolder(val view: View): RecyclerView.ViewHolder(view){

    // binding
    private val binding = EventContainerBinding.bind(view)

    fun bind(event:EventResponse?){
        binding.tvName.text = event?.name ?: "No data found"
        binding.tvCountry.text = event?.country ?: "No data found"
        binding.tvDate.text = event?.dateStr ?: "No data found"
        binding.tvVenue.text = event?.venue ?: "No data found"
    }
}
