package com.maxdev.glitenetwork.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.maxdev.glitenetwork.BaseActivity
import com.maxdev.glitenetwork.R
import com.maxdev.glitenetwork.databinding.ActivityMainBinding
import com.maxdev.glitenetwork.features.newScreen
import com.maxdev.glitenetwork.settings.AppLanguageObj
import com.maxdev.glitenetwork.utils.ConfigParam
import com.maxdev.glitenetwork.utils.NewScreen
import com.maxdev.glitenetwork.utils.Setting

class MainActivity : BaseActivity() {

    val appL by lazy { AppLanguageObj() }
    lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var isFirstTime = true
    private var lastAdShownTime = 0L
    private var pendingAction: (() -> Unit)? = null
    private var exitFragmentContainer: ConstraintLayout? = null

    var fragmentManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //  initEvent()

        fragmentManager = supportFragmentManager


        // Initialize the Mobile Ads SDK.
        sharedPreferences = getSharedPreferences("Myrehgffs", Context.MODE_PRIVATE)
        buttonClicks()
        darkMode()
        appL.checkApp(this)
        if (!isNotificationPermission()) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 11)
        }

    }

    private fun isNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }


    private fun darkMode() {
        val savedMode = sharedPreferences.getInt("MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        when (savedMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                binding.settingsIconeBtn.setImageResource(R.drawable.settingsicone_white)
                binding.moonIconeBtn.setImageResource(R.drawable.baseline_dark_mode_24)
                binding.fourGTitleLte.setTextColor(resources.getColor(R.color.white))
                binding.clHowToUse.setBackgroundResource(R.drawable.round_shape_dark)
                binding.settinngs.setBackgroundResource(R.drawable.round_shape_dark)
                binding.spped.setBackgroundResource(R.drawable.round_shape_dark)
                binding.sped.setBackgroundResource(R.drawable.round_shape_dark)
                binding.ped.setBackgroundResource(R.drawable.round_shape_dark)
                binding.pd.setBackgroundResource(R.drawable.round_shape_dark)
                binding.howtoUseBtn.setTextColor(resources.getColor(R.color.white))
                binding.ping4g.setTextColor(resources.getColor(R.color.white))
                binding.settinngs.setTextColor(resources.getColor(R.color.white))
                binding.spped.setTextColor(resources.getColor(R.color.white))
                binding.sped.setTextColor(resources.getColor(R.color.white))
                binding.ped.setTextColor(resources.getColor(R.color.white))
                binding.pd.setTextColor(resources.getColor(R.color.white))

            }

            AppCompatDelegate.MODE_NIGHT_NO -> {
                binding.howtoUseBtn.setTextColor(resources.getColor(R.color.lightTextC))
                binding.clHowToUse.setBackgroundResource(R.drawable.roundedcgreen)
                binding.fourGTitleLte.setTextColor(resources.getColor(R.color.black))
                binding.settingsIconeBtn.setImageResource(R.drawable.settingsicone)
                binding.moonIconeBtn.setImageResource(R.drawable.moonicone)
                binding.settinngs.setTextColor(resources.getColor(R.color.lightTextC))
                binding.spped.setTextColor(resources.getColor(R.color.lightTextC))
                binding.sped.setTextColor(resources.getColor(R.color.lightTextC))
                binding.ped.setTextColor(resources.getColor(R.color.lightTextC))
                binding.pd.setTextColor(resources.getColor(R.color.lightTextC))
                binding.clHowToUse.setBackgroundResource(R.drawable.roundedc)
            }

            else -> {
            }
        }
        binding.moonIconeBtn.setOnClickListener {
            val currentMode = AppCompatDelegate.getDefaultNightMode()
            val newMode = if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.MODE_NIGHT_NO
            } else {
                AppCompatDelegate.MODE_NIGHT_YES
            }
            AppCompatDelegate.setDefaultNightMode(newMode)
            sharedPreferences.edit().putInt("MODE", newMode).apply()
            recreate()
        }
    }

    private fun buttonClicks() {
        binding.clHowToUse.setOnClickListener {

                newScreen(HowtoUseActivity::class.java)
        }
        binding.settingsIconeBtn.setOnClickListener {
            NewScreen.start(this, SettingsActivity::class.java)
        }
        binding.settingLayoutBtn.setOnClickListener {

                newScreen(LteSettingsActivity::class.java)

        }
        binding.dataUsageBtn.setOnClickListener {

                newScreen(DataUsageActivity::class.java)


        }
        binding.speedTestBtn.setOnClickListener {

                newScreen(SpeedTestActivity::class.java)

        }
        binding.simInfoBtn.setOnClickListener {

                newScreen(SimInfoActivity::class.java)

        }
        binding.signalSrengthBtn.setOnClickListener {

                newScreen(SignalStrengthActivity::class.java)

        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {

            showExitDialog()
    }

    private fun showExitDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.exit_dialog_new, null)
        val dialog = AlertDialog.Builder(
            this,
            android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen
        )
            .setView(dialogView)
            .setCancelable(true)
            .create()

        dialogView.findViewById<ConstraintLayout>(R.id.speedTestBtn).setOnClickListener {
            NewScreen.start(this, SpeedTestActivity::class.java)
        }

        dialogView.findViewById<ConstraintLayout>(R.id.dataUsageBtn).setOnClickListener {
            NewScreen.start(this, DataUsageActivity::class.java)
        }

        dialogView.findViewById<ConstraintLayout>(R.id.signalSrengthBtn).setOnClickListener {
            NewScreen.start(this, SignalStrengthActivity::class.java)
        }

        dialogView.findViewById<ConstraintLayout>(R.id.simInfoBtn).setOnClickListener {
            NewScreen.start(this, SimInfoActivity::class.java)
        }

        dialogView.findViewById<TextView>(R.id.tv_cancelBtn).setOnClickListener {
            dialog.dismiss()
        }

        dialogView.findViewById<TextView>(R.id.tv_okBtn).setOnClickListener {
            dialog.dismiss()
            finishAffinity()
        }

        dialog.show()
    }


//    private fun showExitDialog() {
//        val dialogView = LayoutInflater.from(this).inflate(R.layout.exit_dialog_new, null)
//        val dialog = AlertDialog.Builder(
//            this,
//            android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen
//        )
//            .setView(dialogView)
//            .setCancelable(true)
//            .create()
//
//        val nativeAdId = getString(R.string.how_to_use_native) // Your Native Ad ID
//        NativeAdsManager.CheckNative(this, window.decorView.rootView, nativeAdId)
//
////        MobileAds.initialize(this@MainActivity)
////        val adLoader = AdLoader.Builder(this@MainActivity, getString(R.string.exit_native))
////            .forNativeAd { nativeAd ->
////                val styles = NativeTemplateStyle.Builder()
////                    .withMainBackgroundColor(ColorDrawable(Color.parseColor("#EFEDED")))
////                    .build()
////                val template = dialogView.findViewById<TemplateView>(R.id.my_storage_template)
////                template.setStyles(styles)
////                template.setNativeAd(nativeAd)
////                template.visibility = View.VISIBLE
////            }
////            .build()
////        adLoader.loadAd(AdRequest.Builder().build())
//
//        dialogView.findViewById<ConstraintLayout>(R.id.speedTestBtn).setOnClickListener {
//            NewScreen.start(this, SpeedTestActivity::class.java)
//        }
//
//        dialogView.findViewById<ConstraintLayout>(R.id.dataUsageBtn).setOnClickListener {
//            NewScreen.start(this, DataUsageActivity::class.java)
//        }
//
//        dialogView.findViewById<ConstraintLayout>(R.id.signalSrengthBtn).setOnClickListener {
//            NewScreen.start(this, SignalStrengthActivity::class.java)
//        }
//
//        dialogView.findViewById<ConstraintLayout>(R.id.simInfoBtn).setOnClickListener {
//            NewScreen.start(this, SimInfoActivity::class.java)
//        }
//
//        dialogView.findViewById<TextView>(R.id.tv_cancelBtn).setOnClickListener {
//            dialog.dismiss()
//        }
//
//        dialogView.findViewById<TextView>(R.id.tv_okBtn).setOnClickListener {
//            dialog.dismiss()
//            finishAffinity()
//        }
//
//        dialog.show()
//    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.exitFragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
