package com.ennoblesoft.androidheterogeneousrecyclerview.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ennoblesoft.androidheterogeneousrecyclerview.models.VerticalModel
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.ennoblesoft.androidheterogeneousrecyclerview.R
import com.squareup.picasso.Picasso

class VerticalAdapter(context: Context, data: List<VerticalModel>) :
    RecyclerView.Adapter<VerticalAdapter.VerticalViewHolder>() {
    var items: List<VerticalModel> = data
    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val view = inflater.inflate(R.layout.item_vertical_rv, parent, false)
        return VerticalViewHolder(view)
    }

    override
    fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        val item = items[position]

        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description
        Picasso.get()
            .load(item.posterUrl)
            .resize(50, 50)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.ivPoster)
    }

    override
    fun getItemCount(): Int {
        return items.size
    }

    class VerticalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        var ivPoster: ImageView = itemView.findViewById(R.id.ivPoster)
    }
}