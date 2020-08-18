package com.medha.myapp

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class MyAdapter(val context: Context, var destinationList: ArrayList<Destination>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        Log.i("MyAdapter", "onCreateViewHolder")

        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = destinationList.size

    override fun onBindViewHolder(itemViewHolder: MyViewHolder, position: Int) {

        Log.i("MyAdapter", "onBindViewHolder $position")

        val destination = destinationList[position]
        itemViewHolder.setData(destination, position)
        itemViewHolder.setListeners()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var currentPosition: Int        = -1
        var destination: Destination?   = null

        fun setData(destination: Destination, position: Int) {
            itemView.apply {
                txvPlaceName.text = destination.placeName
                imvPlace.setImageResource(destination.imageId)
            }

            this.currentPosition = position
            this.destination = destination
        }

        fun setListeners() {
            itemView.apply {
                imvDelete.setOnClickListener(this@MyViewHolder)
                imvMakeCopy.setOnClickListener(this@MyViewHolder)
                rootCardView.setOnClickListener(this@MyViewHolder)
            }
        }

        override fun onClick(v: View?) {

            when (v!!.id) {
                R.id.imvDelete -> deleteItem()
                R.id.imvMakeCopy -> addItem()
                R.id.rootCardView -> openExploreActivity()
            }
        }
        fun openExploreActivity()
        {
            val intent = Intent(context,ExploreActivity::class.java )
            intent.putExtra("place_name", destination?.placeName.toString())
            context.startActivity(intent)
        }

        fun deleteItem() {

            destinationList.removeAt(currentPosition)
            notifyItemRemoved(currentPosition)
            notifyItemRangeChanged(currentPosition, destinationList.size)
        }

        fun addItem() {

            destinationList.add(currentPosition, destination!!)
            notifyItemInserted(currentPosition)
            notifyItemRangeChanged(currentPosition, destinationList.size)
        }
    }
}



