package com.maxdev.glitenetwork.splash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.maxdev.glitenetwork.BaseActivity
import com.maxdev.glitenetwork.databinding.ActivitySplashBinding
import com.maxdev.glitenetwork.settings.AppLanguageObj
import com.maxdev.glitenetwork.ui.MainActivity
import com.maxdev.glitenetwork.ui.SelectLangActivity
import com.maxdev.glitenetwork.utils.Setting
import com.maxdev.glitenetwork.utils.SharedPrefObj
import com.maxdev.glitenetwork.utils.isOnline
import kotlinx.android.synthetic.main.activity_splash.progressBar


class SplashActivity : BaseActivity() {

    private var isAdClosed: Boolean = false
    var animator: ObjectAnimator? = null
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("Myrehgffs", Context.MODE_PRIVATE)
    }
    val appL by lazy { AppLanguageObj() }
    private var progressStatus = 0
    private var handler: Handler? = null
    private lateinit var slideUp: ImageView
    private lateinit var title: TextView
    lateinit var binding: ActivitySplashBinding
    private val launchCountKey = "LAUNCH_COUNT"


    private var skuListSubscriptionsList: ArrayList<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        skuListSubscriptionsList = ArrayList()
        skuListSubscriptionsList!!.add(Setting.threeDayTrails)
        skuListSubscriptionsList!!.add(Setting.monthlyAds)
        skuListSubscriptionsList!!.add(Setting.removeAdsOneYearId)



        appL.checkApp(this)
        darkMode()

        animateProgressBar()

        // Increment launch count
        incrementLaunchCount()


    }


    private fun incrementLaunchCount() {
        val launchCount = sharedPreferences.getInt(launchCountKey, 0)
        sharedPreferences.edit().putInt(launchCountKey, launchCount + 1).apply()
    }

    private fun getLaunchCount(): Int {
        return sharedPreferences.getInt(launchCountKey, 0)
    }

    private fun startNextActivity() {
        when (getLaunchCount()) {
            1 -> {
                startActivity(Intent(applicationContext, SelectLangActivity::class.java))
            }

            2 -> {

                startActivity(Intent(applicationContext, SecondTimeOnly::class.java))
            }

            else -> {
                if (SharedPrefObj.getToken(this) != null) {

                    startActivity(Intent(applicationContext, MainActivity::class.java))
                } else {

                    startActivity(Intent(applicationContext, SelectLangActivity::class.java))
                }
            }
        }
        finish()
    }

    private fun animateProgressBar() {
        animator = if (!isOnline()) {
            ObjectAnimator.ofInt(progressBar, "progress", 0, progressBar.max).setDuration(3000)
        } else {
            ObjectAnimator.ofInt(progressBar, "progress", 0, progressBar.max).setDuration(12000)
        }
        animator?.addUpdateListener { animation ->
            progressBar.progress.apply {
                if (this == progressBar.max) {

                }
            }
        }

        // Add an end listener to the animator
        animator?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                startNextActivity()

            }
        })
        animator?.start()
    }

    private fun darkMode() {
        val savedMode = sharedPreferences.getInt("MODE", AppCompatDelegate.MODE_NIGHT_YES)
        when (savedMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                // Your existing code for dark mode
            }

            AppCompatDelegate.MODE_NIGHT_NO -> {
                // Your existing code for light mode

            }
        }
    }

    private fun popupSnackbarForCompleteUpdate() {
        recreate()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode != RESULT_OK) {
                // The user has cancelled or failed to complete the update
                // Prompt the update again or close the app
                // Optionally, you can close the app if you do not want to allow usage without the update
                // finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        animator?.resume()

    }

    override fun onPause() {
        super.onPause()
        animator?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        animator?.removeAllListeners()
    }


}