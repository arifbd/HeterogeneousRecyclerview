package com.ennoblesoft.androidheterogeneousrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ennoblesoft.androidheterogeneousrecyclerview.adapters.MainAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ennoblesoft.androidheterogeneousrecyclerview.utils.JsonHelper

class MainActivity : AppCompatActivity() {
    private val objects:MutableList<Any> = ArrayList()
    private var jsonHelper:JsonHelper ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jsonHelper = JsonHelper(this)
        val recyclerView = findViewById<RecyclerView>(R.id.rvMain)
        val adapter = MainAdapter(this, getObject())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getObject(): ArrayList<Any> {
        jsonHelper?.readHorizontalItemsJSON()?.get(0)?.let { objects.add(it) }
        jsonHelper?.readVerticalItemsJSON()?.get(0)?.let { objects.add(it) }
        return objects as ArrayList<Any>
    }
}
