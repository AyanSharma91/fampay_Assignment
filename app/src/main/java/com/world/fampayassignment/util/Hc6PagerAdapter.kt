package com.world.fampayassignment.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.world.fampayassignment.databinding.SingleRowBoxHc6Binding
import com.world.fampayassignment.model.Card


class Hc6PagerAdapter(val context: Context ,  private var card: ArrayList<Card>) :
    RecyclerView.Adapter<Hc6PagerAdapter.PagerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {

        return PagerViewHolder(
            SingleRowBoxHc6Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        with(holder) {
            with(card[position]) {
                Picasso.get().load(this.icon.imageUrl)
                    .transform(ResizeTransformation(2400))
                    .into(binding.bgImage)

                binding.title.text = Html.fromHtml(
                    SpannableString().formatString(
                    this.title,
                    this.formattedTitle.text,
                    this.formattedTitle.entities
                ))

                binding.root.setOnClickListener {
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(this.url)))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return card.size
    }

    class PagerViewHolder(val binding: SingleRowBoxHc6Binding) :
        RecyclerView.ViewHolder(binding.root)



}