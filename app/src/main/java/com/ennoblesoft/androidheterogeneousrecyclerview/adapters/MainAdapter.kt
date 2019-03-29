package com.ennoblesoft.androidheterogeneousrecyclerview.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ennoblesoft.androidheterogeneousrecyclerview.R
import com.ennoblesoft.androidheterogeneousrecyclerview.models.HorizontalModel
import com.ennoblesoft.androidheterogeneousrecyclerview.models.VerticalModel
import com.ennoblesoft.androidheterogeneousrecyclerview.utils.Constants.HORIZONTAL
import com.ennoblesoft.androidheterogeneousrecyclerview.utils.Constants.VERTICAL
import com.ennoblesoft.androidheterogeneousrecyclerview.utils.JsonHelper

class MainAdapter(private val context: Context, private val items: ArrayList<Any>?=null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val jsonHelper = JsonHelper(context)

    //this method returns the number according to the Vertical/Horizontal object
    override
    fun getItemViewType(position: Int): Int {
        if (items?.get(position) is HorizontalModel)
            return HORIZONTAL
        return if (items?.get(position) is VerticalModel) VERTICAL else -1
    }

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View
        val holder: RecyclerView.ViewHolder
        when (viewType) {
            HORIZONTAL -> {
                view = inflater.inflate(R.layout.layout_horizontal_recycler_view, parent, false)
                holder = HorizontalViewHolder(view)
            }
            VERTICAL -> {
                view = inflater.inflate(R.layout.layout_vertical_recycler_view, parent, false)
                holder = VerticalViewHolder(view)
            }
            else -> {
                view = inflater.inflate(R.layout.layout_horizontal_recycler_view, parent, false)
                holder = HorizontalViewHolder(view)
            }
        }
        return holder
    }

    override
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == HORIZONTAL)
            horizontalView(holder as HorizontalViewHolder)
        else if (holder.itemViewType == VERTICAL)
            verticalView(holder as VerticalViewHolder)
    }

    private fun horizontalView(holder: HorizontalViewHolder) {
        val adapter = HorizontalAdapter(context, jsonHelper.readHorizontalItemsJSON()!!)
        holder.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.recyclerView.adapter = adapter
    }

    private fun verticalView(holder: VerticalViewHolder) {
        val adapter = VerticalAdapter(context, jsonHelper.readVerticalItemsJSON()!!)
        holder.recyclerView.layoutManager = LinearLayoutManager(context)
        holder.recyclerView.adapter = adapter
    }

    override
    fun getItemCount(): Int {
        return items?.size ?:0
    }

    inner class HorizontalViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var recyclerView: RecyclerView = itemView.findViewById(R.id.rvHorizontal)
    }

    inner class VerticalViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var recyclerView: RecyclerView = itemView.findViewById(R.id.rvVertical)
    }
}