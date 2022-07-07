package com.world.fampayassignment.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.world.fampayassignment.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewModel: CardContainerViewModel by viewModels()
    lateinit var containerLayout: ContainerLayout
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var splashView : ImageView
    lateinit var errorTxt : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     //This function initializes the views
        initializeViews()


        //This sets up the listeners
        setListeners()


    }

    private fun initializeViews(){
        containerLayout = findViewById(R.id.container_layout)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        splashView = findViewById(R.id.splashView)
        errorTxt= findViewById(R.id.error_txt)

    }

    private fun setListeners() {
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isActivated = false
            getCardDataDetails()

        }

        if (!swipeRefreshLayout.isRefreshing) {
            getCardDataDetails()
        }

    }

    private fun getCardDataDetails() {
        viewModel.getCardDataDetails()
        lifecycleScope.launchWhenStarted {
            viewModel.dataState.collect { event ->

                when (event) {
                    is CardContainerViewModel.MainStateEvent.Success -> {

                        containerLayout.populateData(event.result)

                        containerLayout.animate()
                            .alpha(1f)
                            .setDuration(1000)
                            .setListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    containerLayout.visibility = VISIBLE
                                    splashView.visibility=View.GONE
                                }
                            })

                        swipeRefreshLayout.isRefreshing=false
                        swipeRefreshLayout.isActivated=true
                        errorTxt.visibility=View.GONE

                    }
                    is CardContainerViewModel.MainStateEvent.Failure -> {
                        errorTxt.visibility=View.VISIBLE
                        swipeRefreshLayout.isRefreshing=false
                        swipeRefreshLayout.isActivated=true
                    }
                    is CardContainerViewModel.MainStateEvent.Loading -> {
                    }
                    else -> Unit
                }
            }
        }

    }
}