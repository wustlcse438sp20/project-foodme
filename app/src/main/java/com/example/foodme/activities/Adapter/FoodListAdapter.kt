package com.example.foodme.activities.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.example.foodme.activities.RouletteActivity
import com.example.foodme.R

class FoodListAdapter (private var activity: RouletteActivity, private var foods: ArrayList<String>): BaseAdapter(){
    private class ViewHolder(row : View?){
        var foodName: TextView? = null
        var foodIndex : TextView? = null
        var delete : Button? = null

        init{
            this.foodName = row?.findViewById(R.id.foodName)
            this.foodIndex = row?.findViewById(R.id.foodIndex)
            this.delete = row?.findViewById(R.id.foodDeleteButton)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{
        val view: View?
        val viewHolder : ViewHolder
        if (convertView == null) {
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.food_list_row, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val actualFoodName = foods[position]
        viewHolder.foodName?.text = actualFoodName
        viewHolder.foodIndex?.text = Integer.toString(position)
        viewHolder.delete?.setOnClickListener{
            activity.deleteFood(position)
        }

        return view as View
    }

    fun getFood(position: Int): Any{
        return foods[position]
    }

    override fun getItem(position: Int): Any {
        return foods[position]
    }

    override fun getItemId(position: Int): Long{
        return position.toLong()
    }

    override fun getCount(): Int{
        return foods.size
    }


}