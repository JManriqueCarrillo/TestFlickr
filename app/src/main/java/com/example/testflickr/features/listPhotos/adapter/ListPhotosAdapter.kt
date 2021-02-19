package com.example.testflickr.features.listPhotos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testflickr.R
import com.example.testflickr.entities.responses.PhotoResponse
import com.example.testflickr.interfaces.ItemClickListener
import com.squareup.picasso.Picasso

class ListPhotosAdapter (private val context: Context, private val dataSet: List<PhotoResponse>, private val callback: ItemClickListener) :
    RecyclerView.Adapter<ListPhotosAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemThumbnail: ImageView
        val itemTitle: TextView
        val itemAuthor: TextView

        init {
            itemThumbnail = view.findViewById(R.id.item_thumbnail)
            itemTitle = view.findViewById(R.id.item_title)
            itemAuthor = view.findViewById(R.id.item_author)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_thumbnail, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Picasso.with(context).load("https://live.staticflickr.com/${dataSet[position].server}/${dataSet[position].id}_${dataSet[position].secret}.jpg").into(viewHolder.itemThumbnail)
        viewHolder.itemTitle.text = dataSet[position].title
        viewHolder.itemAuthor.text = dataSet[position].ownerName
        viewHolder.itemView.setOnClickListener {
            callback.onItemClickListener(dataSet[position])
        }
    }

    override fun getItemCount() = dataSet.size

}
