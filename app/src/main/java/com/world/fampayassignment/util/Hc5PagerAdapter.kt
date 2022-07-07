package com.world.fampayassignment.util

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.world.fampayassignment.databinding.SingleRowBoxHc5Binding
import com.world.fampayassignment.model.Card


class Hc5PagerAdapter(private val context: Context, private var card: ArrayList<Card>) :
    RecyclerView.Adapter<Hc5PagerAdapter.PagerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {

        return PagerViewHolder(

            SingleRowBoxHc5Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        with(holder) {
            with(card[position]) {
                Picasso.get().load(this.bgImage.imageUrl)
                    .transform(ResizeTransformation(1200))
                    .into(binding.bgImage)


                binding.bgImage.setOnClickListener{
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(this.url)))
                }

                if(!this.bgColor.isNullOrEmpty())
                    binding.bgImage.setBackgroundColor((Color.parseColor(this.bgColor)))
            }
        }
    }

    override fun getItemCount(): Int {
        return card.size
    }

    class PagerViewHolder(val binding:SingleRowBoxHc5Binding) :
        RecyclerView.ViewHolder(binding.root)




}