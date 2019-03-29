package com.ennoblesoft.androidheterogeneousrecyclerview.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ennoblesoft.androidheterogeneousrecyclerview.R
import com.ennoblesoft.androidheterogeneousrecyclerview.models.HorizontalModel
import com.squareup.picasso.Picasso

class HorizontalAdapter(context: Context, data: List<HorizontalModel>) :
    RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder>() {
    var items: List<HorizontalModel> = data
    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val view = inflater.inflate(R.layout.item_horizontal_rv, parent, false)
        return HorizontalViewHolder(view)
    }

    override
    fun getItemCount(): Int {
        return items.size
    }

    override
    fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        val item = items[position]

        holder.tvName.text = item.name
        Picasso.get()
            .load(item.imgUrl)
            .resize(50, 50)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.ivPhoto)
    }

    class HorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var ivPhoto: ImageView = itemView.findViewById(R.id.ivPhoto)
    }
}