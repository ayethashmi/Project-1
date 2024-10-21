package com.maxdev.glitenetwork.ui

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.maxdev.glitenetwork.BaseActivity
import com.maxdev.glitenetwork.R
import com.maxdev.glitenetwork.databinding.ActivitySimInfoBinding

class SimInfoActivity : BaseActivity() {
    private val phoneStatePermissionCode = 101
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("Myrehgffs", Context.MODE_PRIVATE)
    }
    lateinit var binding: ActivitySimInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPermission()
        toolBar()
        darkMode()
    }

    private fun darkMode() {
        val savedMode = sharedPreferences.getInt("MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        when (savedMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                binding.toolBar.title.setTextColor(resources.getColor(R.color.white))
                binding.simOne.setTextColor(resources.getColor(R.color.green))
                binding.sim2.setTextColor(resources.getColor(R.color.green))
            }

            AppCompatDelegate.MODE_NIGHT_NO -> {
                binding.toolBar.title.setTextColor(resources.getColor(R.color.black))
                binding.simOne.setTextColor(resources.getColor(R.color.blueC))
                binding.sim2.setTextColor(resources.getColor(R.color.blueC))
            }

            else -> {
            }
        }
    }

    private fun toolBar() {
        binding.toolBar.backBtn.setOnClickListener { onBackPressed() }
        binding.toolBar.removeAdsbtn.visibility = View.GONE
        binding.toolBar.title.setText(resources.getString(R.string.siminfo))
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                phoneStatePermissionCode
            )
        } else {
            getSimDetails()
        }
    }


    private fun getSimDetails() {
        try {
            val subscriptionManager =
                getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager

            // Check for permission
// Check for permission
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val subscriptionInfoList: List<SubscriptionInfo> =
                    subscriptionManager.activeSubscriptionInfoList
                for ((index, subscriptionInfo) in subscriptionInfoList.withIndex()) {
                    val carrierName = subscriptionInfo.carrierName.toString()
                    val displayName = subscriptionInfo.displayName.toString()
                    val countryIso = subscriptionInfo.countryIso
                    val mnc = subscriptionInfo.mnc.toString()

                    // Determine SIM slot and set the details accordingly
                    when (index) {
                        0 -> { // SIM one
                            binding.carrierSimOne.setText(carrierName)
                            binding.countryCOdeSimOne.setText(countryIso)
                            binding.mncSimOneTv.setText(mnc)
                            binding.displayNameSimOne.setText(displayName)
                        }

                        1 -> { // SIM two
                            binding.carrierSimTwo.setText(carrierName)
                            binding.countryCOdeSimTwo.setText(countryIso)
                            binding.mncSimTwoTv.setText(mnc)
                            binding.displayNameSimTwo.setText(displayName)
                        }

                        else -> {
                            // Handle the possibility of more than two SIM slots if necessary
                        }
                    }
                }
            } else {
                println("Permission not granted")
            }
        } catch (e: Exception) {
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == phoneStatePermissionCode && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getSimDetails()
        }
    }
}