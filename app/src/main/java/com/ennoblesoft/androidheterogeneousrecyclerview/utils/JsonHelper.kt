package com.ennoblesoft.androidheterogeneousrecyclerview.utils

import android.content.Context
import com.ennoblesoft.androidheterogeneousrecyclerview.models.HorizontalModel
import com.ennoblesoft.androidheterogeneousrecyclerview.models.VerticalModel
import org.json.JSONObject

open class JsonHelper(private var context: Context) {
    private var verticalItemList: MutableList<VerticalModel>? = null
    private var horizontalItemList: MutableList<HorizontalModel>? = null

    open fun readVerticalItemsJSON(): List<VerticalModel>? {
        if (verticalItemList == null)
            verticalItemList = ArrayList()

        try {
            val jsonObject = JSONObject(loadJSONFromAssets())
            val jsonArray = jsonObject.getJSONArray("vertical_items")
            val k = jsonArray.length()

            for (i in 0 until k) {
                val verticalItemObject = jsonArray.getJSONObject(i)
                val title = verticalItemObject.getString("title")
                val description = verticalItemObject.getString("description")
                val posterUrl = verticalItemObject.getString("photo")
                val model = VerticalModel(title = title, description = description, posterUrl = posterUrl)
                verticalItemList?.add(model)
            }
            return verticalItemList
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    open fun readHorizontalItemsJSON(): List<HorizontalModel>? {
        if (horizontalItemList == null)
            horizontalItemList = ArrayList()

        try {
            val jsonObject = JSONObject(loadJSONFromAssets())
            val jsonArray = jsonObject.getJSONArray("horizontal_items")
            val k = jsonArray.length()

            for (i in 0 until k) {
                val horizontalItemObject = jsonArray.getJSONObject(i)
                val name = horizontalItemObject.getString("name")
                val imgUrl = horizontalItemObject.getString("photo")
                val model = HorizontalModel(name = name, imgUrl = imgUrl)
                horizontalItemList?.add(model)
            }
            return horizontalItemList
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    private fun loadJSONFromAssets(): String? {
        val json: String
        try {
            val inputStream = context.assets.open("fake_items.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return json
    }
}