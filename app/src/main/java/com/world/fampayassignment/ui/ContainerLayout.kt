package com.world.fampayassignment.ui


import android.content.Context
import android.content.SharedPreferences
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.world.fampayassignment.util.HC3PagerAdapter
import com.world.fampayassignment.R
import com.world.fampayassignment.model.Card
import com.world.fampayassignment.model.CardModel
import com.world.fampayassignment.util.HC9Adapter
import com.world.fampayassignment.util.Hc1PagerAdapter
import com.world.fampayassignment.util.Hc5PagerAdapter
import com.world.fampayassignment.util.Hc6PagerAdapter


class ContainerLayout : RelativeLayout {


    lateinit var hc3Viewpager : ViewPager2
    lateinit var hc6Viewpager : ViewPager2
    lateinit var hc5ViewPager : ViewPager2
    lateinit var hc9recyclerview : RecyclerView
    lateinit var hc1viewPager : ViewPager2
    lateinit var remindBtn : RelativeLayout
    lateinit var dismissBtn : RelativeLayout
    lateinit var hc3CardView : CardView
    lateinit var sharedpreferences: SharedPreferences

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context)
    }


    private fun initView(context: Context) {


       inflate(context, R.layout.container_layout, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        //Initializes our views
         initializeViews()


       //Handle Preferences
        sharedpreferences = context!!.getSharedPreferences("HC3_VISIBILITY", Context.MODE_PRIVATE)



        remindBtn.setOnClickListener{
            fadeOutAndHideImage(hc3CardView)
        }

        dismissBtn.setOnClickListener{

            val editor: SharedPreferences.Editor = sharedpreferences.edit()
            editor.putBoolean("isVisible",false)
            editor.apply()
            fadeOutAndHideImage(hc3CardView)
        }



    }


    fun initializeViews(){

        hc3Viewpager = findViewById(R.id.hc3_viewpager)
        hc6Viewpager= findViewById(R.id.hc6_viewpager)
        hc5ViewPager = findViewById(R.id.hc5_viewpager)
        hc9recyclerview = findViewById(R.id.hc9_recyclerview)
        hc1viewPager = findViewById(R.id.hc1_viewpager)
        remindBtn = findViewById(R.id.remindBtn)
        dismissBtn = findViewById(R.id.dismissBtn)
        hc3CardView = findViewById(R.id.hc3cardview)

    }


    fun populateData(cardModel : CardModel ){

        hc3Viewpager.isUserInputEnabled=false
        hc6Viewpager.isUserInputEnabled=false
        hc5ViewPager.isUserInputEnabled=false
        hc1viewPager.isUserInputEnabled=false



        val hc3 = ArrayList<Card>()
        val hc6 = ArrayList<Card>()
        val hc5 = ArrayList<Card>()
        val hc9 = ArrayList<Card>()
        val hc1 = ArrayList<Card>()
        for(x in cardModel.cardGroups){
            if(x.designType=="HC3"){
                for(card in x.cards){
                    hc3.add(card)
                }
                if(hc3.size>1 && x.isScrollable) hc3Viewpager.isUserInputEnabled=true
            }
            else if(x.designType=="HC6"){
                for(card in x.cards) hc6.add(card)
                if(hc6.size>1 && x.isScrollable) hc6Viewpager.isUserInputEnabled=true
            }
            else if(x.designType=="HC5"){
                for(card in x.cards) hc5.add(card)
                if(hc5.size>1 && x.isScrollable) hc5ViewPager.isUserInputEnabled=true
            }
            else if(x.designType=="HC9"){
                for(card in x.cards) hc9.add(card)
            }
            else if(x.designType=="HC1"){
                for(card in x.cards) hc1.add(card)
                if(hc1.size>1 && x.isScrollable) hc1viewPager.isUserInputEnabled=true
            }
        }
        if(hc3.isNotEmpty()){

            if(!sharedpreferences.getBoolean("isVisible", true))
                hc3CardView.visibility=View.GONE
            else
            hc3CardView.visibility=View.VISIBLE

            hc3Viewpager.adapter = HC3PagerAdapter(context , hc3 , remindBtn , dismissBtn )
        }

        if(hc6.isNotEmpty()){
            hc6Viewpager.visibility = View.VISIBLE
            hc6Viewpager.adapter = Hc6PagerAdapter(context , hc6)
        }

        if(hc5.isNotEmpty()){
            hc5ViewPager.visibility = View.VISIBLE
            hc5ViewPager.adapter = Hc5PagerAdapter(context , hc5)
        }


        if(hc9.isNotEmpty()){
            hc9recyclerview.visibility = View.VISIBLE
            val adapter = HC9Adapter(context , hc9)
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL , false)
            hc9recyclerview.layoutManager = layoutManager
            hc9recyclerview.adapter = adapter

        }

        if(hc1.isNotEmpty()){
            hc1viewPager.visibility=View.VISIBLE
            hc1viewPager.adapter = Hc1PagerAdapter(context , hc1)
        }




    }

    //hides the image with animation
    private fun fadeOutAndHideImage(img: View) {
        val fadeOut: Animation = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator()
        fadeOut.duration = 400
        fadeOut.setAnimationListener(object : AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {
                img.visibility = GONE
            }

            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
        })
        img.startAnimation(fadeOut)
    }


}