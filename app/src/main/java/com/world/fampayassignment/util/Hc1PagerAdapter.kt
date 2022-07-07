package com.world.fampayassignment.util

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.world.fampayassignment.databinding.SingleRowBoxHc1Binding
import com.world.fampayassignment.model.Card


class Hc1PagerAdapter(val context: Context, private var card: ArrayList<Card>) :
    RecyclerView.Adapter<Hc1PagerAdapter.PagerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(
            SingleRowBoxHc1Binding.inflate(
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
                    )
                )

                if (!this.bgColor.isNullOrEmpty())
                    binding.bgLayout.setBackgroundColor(Color.parseColor(this.bgColor))

                binding.bgLayout.setOnClickListener {
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(this.url)))
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return card.size
    }

    class PagerViewHolder(val binding: SingleRowBoxHc1Binding) : RecyclerView.ViewHolder(binding.root)

}