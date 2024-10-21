package com.maxdev.glitenetwork.ui

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.maxdev.glitenetwork.BaseActivity
import com.maxdev.glitenetwork.R
import com.maxdev.glitenetwork.databinding.ActivitySignalStrengthBinding
import com.maxdev.glitenetwork.utils.ConfigParam
import timber.log.Timber

class SignalStrengthActivity : BaseActivity() {
    lateinit var binding: ActivitySignalStrengthBinding
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("Myrehgffs", Context.MODE_PRIVATE)
    }
    private val REQUEST_CODE_PERMISSIONS = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignalStrengthBinding.inflate(layoutInflater)
        setContentView(binding.root)


        actionBar?.hide()
        toolBar()
        darkMode()
        if (arePermissionsGranted()) {
            val networkStrength = getNetworkStrength()
            val (strength1, strength2) = getNetworkStrength()
            updateSpeedMeter(strength1, strength2)
            // Do something with the network strength
        } else {
            requestPermissions()
        }


    }

    private fun darkMode() {
        val savedMode = sharedPreferences.getInt("MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        when (savedMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                binding.toolBar.title.setTextColor(resources.getColor(R.color.white))
                binding.incomingText.setTextColor(resources.getColor(R.color.white))
                binding.pointerSpeedometerText.setTextColor(resources.getColor(R.color.white))
            }

            AppCompatDelegate.MODE_NIGHT_NO -> {
                binding.toolBar.title.setTextColor(resources.getColor(R.color.black))
                binding.incomingText.setTextColor(resources.getColor(R.color.black))
                binding.pointerSpeedometerText.setTextColor(resources.getColor(R.color.black))
            }

            else -> {
            }
        }
    }

    private fun toolBar() {
        binding.toolBar.backBtn.setOnClickListener { onBackPressed() }
        binding.toolBar.removeAdsbtn.visibility = View.GONE
        binding.toolBar.title.setText(resources.getString(R.string.signlaStrengh))
    }

    private fun arePermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE
            ),
            REQUEST_CODE_PERMISSIONS
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_PERMISSIONS -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    val networkStrength = getNetworkStrength()
                    // Do something with the network strength

                    val (strength1, strength2) = getNetworkStrength()
                    updateSpeedMeter(strength1, strength2)
                    // Do something with the network strength

                } else {
                    Timber.e("Permissions denied.")
                }
                return
            }
        }
    }

    private fun dbmToPercentage(dbm: Int): Int {
        // Signal strength is usually in the range of -120 dBm (poor) to -40 dBm (excellent).
        // Anything lower than -120 dBm will be considered as 0%.
        // Anything higher than -40 dBm will be considered as 100%.
        if (dbm <= -120) return 0 // Poor signal
        if (dbm >= -40) return 100 // Excellent signal
        // Convert dBm to percentage
        // (dbm + 120) is the signal strength on a scale from 0 to 80.
        // We then divide by 80 to normalize it to a value between 0 and 1.
        // Finally, we multiply by 100 to convert it to a percentage.
        return ((dbm + 120) * 100) / 80
    }

    private fun getNetworkStrength(): Pair<Int, Int> {
        var strength1 = -1
        var strength2 = -1

        val manager =
            getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val telephonyManager =
                applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

            // Check if permissions are granted, return default values if not
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return Pair(strength1, strength2)
            }

            if (telephonyManager.allCellInfo != null) {
                // ... (rest of your code)
            }
        }

        Timber.i("final strength   sim1 $strength1  sim2 $strength2")
        return Pair(strength1, strength2)
    }

    private fun updateSpeedMeter(strength1: Int, strength2: Int) {
        val percentage1 = dbmToPercentage(strength1)
        val percentage2 = dbmToPercentage(strength2)

        val handler = Handler(Looper.getMainLooper())

        // Post a delayed Runnable to run after one second (1000 milliseconds)
        handler.postDelayed({
            binding.pointerSpeedometerOne.setSpeedAt(percentage1.toFloat())
            binding.pointerSpeedometerTwo.setSpeedAt(percentage2.toFloat())
            binding.incomingText.text =
                "${resources.getString(R.string.sim1Sg)} -${percentage1} dBm"
            binding.pointerSpeedometerText.text =
                "${resources.getString(R.string.sim2Sg)} -${percentage2} dBm"

            // Update the live percentage in the pointerSpeedometerPer TextView
            binding.pointerSpeedometerPer.text =
                "Live Percentage: ${(percentage1 + percentage2) / 2}%"
        }, 1000)
    }
}
