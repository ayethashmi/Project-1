package com.maxdev.glitenetwork.ui

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager.widget.ViewPager
import com.maxdev.glitenetwork.BaseActivity
import com.maxdev.glitenetwork.R
import com.maxdev.glitenetwork.adapters.ImageSliderAdapter
import com.maxdev.glitenetwork.databinding.ActivityHowtoUseBinding

class HowtoUseActivity : BaseActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    // creating object of ViewPager
    private lateinit var mViewPager: ViewPager

    // images array
    lateinit var binding: ActivityHowtoUseBinding
    private val images = intArrayOf(
        R.drawable.screenon, R.drawable.screentwo, R.drawable.screenthre, R.drawable.screenfour
    )

    // Creating Object of ViewPagerAdapter
    private lateinit var mViewPagerAdapter: ImageSliderAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHowtoUseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("Myrehgffs", Context.MODE_PRIVATE)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding.doneBtn.setOnClickListener {
            val nextItem = mViewPager.currentItem + 1
            if (nextItem < mViewPagerAdapter.count) {
                mViewPager.currentItem = nextItem
            } else {
                finish()
            }
        }
        binding.skipBtn.setOnClickListener {
            val previousItem = mViewPager.currentItem - 1
            if (previousItem >= 0) {
                mViewPager.currentItem = previousItem
            } else {

            }
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                )
        // Initializing the ViewPager Object
        mViewPager = findViewById(R.id.viewPager)

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = ImageSliderAdapter(this, images)

        // Adding the Adapter to the ViewPager
        mViewPager.adapter = mViewPagerAdapter
        binding.dotsIndicator.attachTo(mViewPager)
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // Not needed for this implementation
            }

            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    binding.skipBtn.setOnClickListener { onBackPressed() }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                // Not needed for this implementation
            }
        })
    }

}