package com.world.fampayassignment.util

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.world.fampayassignment.databinding.SingleRowBoxHc3Binding
import com.world.fampayassignment.model.Card


class HC3PagerAdapter(private val context: Context ,  private var card: ArrayList<Card> , var remindBtn : RelativeLayout , var dismissBtn : RelativeLayout) :
    RecyclerView.Adapter<HC3PagerAdapter.PagerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {

        return PagerViewHolder(
            SingleRowBoxHc3Binding.inflate(
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
                    .transform(ResizeTransformation(2400))
                    .into(binding.bgImage)



                binding.title.text = Html.fromHtml(
                    SpannableString().formatString(
                    this.title,
                    this.formattedTitle.text,
                    this.formattedTitle.entities!!
                ))
                binding.description.text = SpannableString().formatString(
                    this.description,
                    this.formattedDescription.text,
                    this.formattedDescription.entities!!
                )

                binding.root.setOnClickListener {
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(this.url)))
                }

                if(!this.bgColor.isNullOrEmpty())
                binding.root.setBackgroundColor(Color.parseColor(this.bgColor))

                 binding.root.setOnLongClickListener {
                     binding.root.animate()
                         .translationX(((binding.root.width*1.5 - (binding.root.width))).toFloat())
                         .setInterpolator(AccelerateInterpolator()).duration = 200

                      remindBtn.visibility= View.VISIBLE
                      dismissBtn.visibility = View.VISIBLE
                     true
                 }

                binding.actionBtn.text = this.cta[0].text
                binding.actionBtn.setTextColor(Color.parseColor(this.cta[0].textColor))
                binding.actionBtn.setBackgroundColor(Color.parseColor(this.cta[0].bgColor))
                binding.actionBtn.setOnClickListener {
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(this.cta[0].url)))
                }


            }
        }
    }

    override fun getItemCount(): Int {
        return card.size
    }

    class PagerViewHolder(val binding: SingleRowBoxHc3Binding) :
        RecyclerView.ViewHolder(binding.root)

}