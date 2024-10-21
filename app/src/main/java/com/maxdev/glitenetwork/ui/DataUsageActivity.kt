package com.maxdev.glitenetwork.ui

import android.Manifest
import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Process
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxdev.glitenetwork.BaseActivity
import com.maxdev.glitenetwork.R
import com.maxdev.glitenetwork.adapters.DataUsagesAdapter
import com.maxdev.glitenetwork.databinding.ActivityDataUsageBinding
import com.maxdev.glitenetwork.modals.UsagesData
import com.maxdev.glitenetwork.utils.ConfigParam
import com.maxdev.glitenetwork.utils.Interval
import com.maxdev.glitenetwork.utils.NetworkType
import com.maxdev.glitenetwork.utils.NetworkUsageManager
import com.maxdev.glitenetwork.utils.Util

class DataUsageActivity : BaseActivity() {
    private val networkUsage by lazy { NetworkUsageManager(this, Util.getSubscriberId(this)) }
    private val usagesDataList: ArrayList<UsagesData> by lazy { ArrayList() }
    val dataUsagesAdapter by lazy { DataUsagesAdapter(usagesDataList) }
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("Myrehgffs", Context.MODE_PRIVATE)
    }
    lateinit var binding: ActivityDataUsageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataUsageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolBar()
        setupPermissions()
        refreshLayout()
        setupData()
        darkMode()
    }

    private fun toolBar() {

        binding.toolBar.removeAdsbtn.visibility = View.GONE
        binding.toolBar.title.setText("Data Usage")
        binding.toolBar.backBtn.setOnClickListener { onBackPressed() }
    }

    private fun darkMode() {
        val savedMode = sharedPreferences.getInt("MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        when (savedMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                binding.title.setTextColor(resources.getColor(R.color.white))
            }

            AppCompatDelegate.MODE_NIGHT_NO -> {
                binding.title.setTextColor(resources.getColor(R.color.black))
            }

            else -> {
            }
        }
    }

    private fun setupData() {
        val handler = Handler(Looper.getMainLooper())
        val runnableCode = object : Runnable {
            override fun run() {
            }
        }

        runnableCode.run()

        val last30DaysWIFI = networkUsage.getMultiUsage(
            Interval.lastMonthDaily, NetworkType.WIFI
        )

        val last30DaysMobile = networkUsage.getMultiUsage(
            Interval.lastMonthDaily, NetworkType.MOBILE
        )

        for (i in last30DaysWIFI.indices) {
            usagesDataList.add(
                UsagesData(
                    Util.formatData(
                        last30DaysMobile[i].downloads,
                        last30DaysMobile[i].uploads
                    )[2],
                    Util.formatData(
                        last30DaysWIFI[i].downloads,
                        last30DaysWIFI[i].uploads
                    )[2],
                    last30DaysWIFI[i].date
                )
            )
        }

        val last7DaysTotalWIFI = networkUsage.getUsage(
            Interval.last7days, NetworkType.WIFI
        )

        val last7DaysTotalMobile = networkUsage.getUsage(
            Interval.last7days, NetworkType.MOBILE
        )

        val last30DaysTotalWIFI = networkUsage.getUsage(
            Interval.last30days, NetworkType.WIFI
        )

        val last30DaysTotalMobile = networkUsage.getUsage(
            Interval.last30days, NetworkType.MOBILE
        )

        usagesDataList.add(
            UsagesData(
                Util.formatData(
                    last7DaysTotalMobile.downloads,
                    last7DaysTotalMobile.uploads
                )[2],
                Util.formatData(
                    last7DaysTotalWIFI.downloads,
                    last7DaysTotalWIFI.uploads
                )[2],
                "Last 7 Days"
            )
        )

        binding.wifiDataThisMonth.text = Util.formatData(
            last30DaysTotalWIFI.downloads,
            last30DaysTotalWIFI.uploads
        )[2]

        binding.mobileDataThisMonth.text = Util.formatData(
            last30DaysTotalMobile.downloads,
            last30DaysTotalMobile.uploads
        )[2]

        binding.monthlyDataUsagesRv.layoutManager = LinearLayoutManager(this)
        binding.monthlyDataUsagesRv.setHasFixedSize(true)
        binding.monthlyDataUsagesRv.adapter = dataUsagesAdapter

    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            this, Manifest.permission.READ_PHONE_STATE
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_PHONE_STATE), 34
            )
        }

        if (!checkUsagePermission()) {
            Toast.makeText(this, "Please allow usage access", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkUsagePermission(): Boolean {
        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        var mode: Int = appOps.checkOpNoThrow(
            "android:get_usage_stats", Process.myUid(),
            packageName

        )
        val granted = mode == AppOpsManager.MODE_ALLOWED
        if (!granted) {
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            startActivity(intent)
            return false
        }
        return true
    }

    private fun refreshLayout() {
        binding.swipRefresLayout.setColorSchemeColors(
            ContextCompat.getColor(this, android.R.color.holo_orange_dark),
            ContextCompat.getColor(this, android.R.color.holo_green_dark),
            ContextCompat.getColor(this, android.R.color.darker_gray),
            ContextCompat.getColor(this, android.R.color.holo_blue_dark)
        )
        binding.swipRefresLayout.setOnRefreshListener {
            usagesDataList.clear()
            setupData()
            binding.swipRefresLayout.isRefreshing = false

        }
    }
}