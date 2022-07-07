package com.world.fampayassignment.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.world.fampayassignment.R
import com.world.fampayassignment.model.Card

class HC9Adapter(val context: Context, private val card: ArrayList<Card>) :
    RecyclerView.Adapter<HC9Adapter.HC9ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HC9ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_row_box_hc9, parent, false)
        return HC9ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return card.size
    }


    override fun onBindViewHolder(holder: HC9ViewHolder, position: Int) {
        val item = card[position]
        Picasso.get().load(item.bgImage.imageUrl)
            .into(holder.bgImage)

        holder.bgImage.setOnClickListener {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
        }

    }

    class HC9ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var bgImage = view.findViewById<ImageView>(R.id.bg_image)

    }


}

