package com.maxdev.glitenetwork.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.maxdev.glitenetwork.BaseActivity
import com.maxdev.glitenetwork.R
import com.maxdev.glitenetwork.databinding.ActivityLteSettingsBinding
import com.maxdev.glitenetwork.utils.ConfigParam
import com.maxdev.glitenetwork.utils.Interval
import com.maxdev.glitenetwork.utils.NetSpeed
import com.maxdev.glitenetwork.utils.NetworkType
import com.maxdev.glitenetwork.utils.NetworkUsageManager
import com.maxdev.glitenetwork.utils.Util

class LteSettingsActivity : BaseActivity() {
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("Myrehgffs", Context.MODE_PRIVATE)
    }
    lateinit var binding: ActivityLteSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLteSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar()
        darkMode()

        val networkUsage = NetworkUsageManager(this, Util.getSubscriberId(this))
        val handler = Handler(Looper.getMainLooper())
        val runnableCode = object : Runnable {
            override fun run() {
                val now = networkUsage.getUsageNow(NetworkType.ALL)
                val speeds = NetSpeed.calculateSpeed(now.timeTaken, now.downloads, now.uploads)
                val todayM = networkUsage.getUsage(Interval.today, NetworkType.MOBILE)
                val todayW = networkUsage.getUsage(Interval.today, NetworkType.WIFI)

//                binding.wifiUsagesTv.text = "WiFi: " + Util.formatData(todayW.downloads, todayW.uploads)[2]
//                binding.dataUsagesTv.text = "Mobile: " + Util.formatData(todayM.downloads, todayM.uploads)[2]
                binding.apply {
                    incomingTv.text = "${speeds[1].speed + speeds[1].unit}"
                    outgoingTv.text = "${speeds[2].speed + speeds[2].unit}"
                }
                var speedPercentage: Float
                try {
                    speedPercentage =
                        speeds[1].speed.toFloat() // Convert the String speed value to Float
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                    speedPercentage = 0f // default value in case of a conversion error
                }
                handler.postDelayed(this, 1000)

            }
        }

        runnableCode.run()
    }

    private fun darkMode() {
        val savedMode = sharedPreferences.getInt("MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        when (savedMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                binding.toolBar.title.setTextColor(resources.getColor(R.color.white))
                binding.spped.setTextColor(resources.getColor(R.color.white))
                binding.data.setTextColor(resources.getColor(R.color.white))

                binding.spped.setBackgroundResource(R.drawable.round_shape_dark)
                binding.data.setBackgroundResource(R.drawable.round_shape_dark)

                binding.arrowDown.setBackgroundResource(R.drawable.lte_arrow_down_ward_white)
                binding.arrowUp.setBackgroundResource(R.drawable.lte_arrow_upward_white)

            }

            AppCompatDelegate.MODE_NIGHT_NO -> {
                binding.toolBar.title.setTextColor(resources.getColor(R.color.black))

                binding.arrowDown.setBackgroundResource(R.drawable.lte_arrow_down_ward)
                binding.arrowUp.setBackgroundResource(R.drawable.lte_arrow_upward)
            }

            else -> {
            }
        }
    }

    private fun toolbar() {
        binding.toolBar.backBtn.setOnClickListener { onBackPressed() }
        binding.toolBar.title.setText(resources.getString(R.string.fourgSettings))
        binding.toolBar.removeAdsbtn.visibility = View.GONE

        binding.below11Android.setOnClickListener {
            try {
                val intent = Intent("android.intent.action.MAIN")
                intent.setClassName("com.android.settings", "com.android.settings.RadioInfo")
                startActivity(intent)
                return@setOnClickListener
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                    this,
                    "Sorry! it seems that your phone doesn't support this feature",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
        }

        binding.above11Android.setOnClickListener {
            try {
                val intent = Intent("android.intent.action.MAIN")
                intent.setClassName("com.android.phone", "com.android.phone.settings.RadioInfo")
                startActivity(intent)
                return@setOnClickListener
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                    this,
                    "Sorry! it seems that your phone doesn't support this feature",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
        }

    }
}