package com.maxdev.glitenetwork.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.maxdev.glitenetwork.BaseActivity
import com.maxdev.glitenetwork.R
import com.maxdev.glitenetwork.databinding.ActivitySecondTimeOnlyBinding
import com.maxdev.glitenetwork.ui.AnimationActivity

class SecondTimeOnly : BaseActivity() {

    var stableData = false
    var impoveStreaming = false
    var onlineGame = false
    var videoCalls = false
    var fastBrowser = false
    var networkSwitch = false
    var counter = 0
    lateinit var binding: ActivitySecondTimeOnlyBinding
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("Myrehgffs", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondTimeOnlyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.nextBtn.setOnClickListener {
            if (counter == 0) {
                Toast.makeText(this, "Please Select Any Category", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this@SecondTimeOnly, AnimationActivity::class.java))
                finish()


            }
        }
        handerSelection()
        darkMode()
    }

    private fun darkMode() {
        val savedMode = sharedPreferences.getInt("MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        when (savedMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                binding.mainSecond.setBackgroundColor(Color.DKGRAY)
                binding.l.setBackgroundColor(Color.DKGRAY)
                binding.nextBtn.setImageResource(R.drawable.tick_white)
                // Your existing code for dark mode
            }

            AppCompatDelegate.MODE_NIGHT_NO -> {
                binding.mainSecond.setBackgroundColor(Color.WHITE)
                binding.l.setBackgroundColor(Color.WHITE)
                binding.nextBtn.setImageResource(R.drawable.tick_black)
                // Your existing code for light mode
            }


        }
    }

    private fun handerSelection() {
        binding.brScannerBox.setOnClickListener {
            if (!videoCalls) {
                counter++
                videoCalls = true
                binding.brScannerBox.setImageResource(
                    R.drawable.baseline_check_box_fill
                )
            } else {
                counter--
                videoCalls = false
                binding.brScannerBox.setImageResource(
                    R.drawable.baseline_check_box_outline_blank_24
                )
            }
        }
        binding.qrScannerBox.setOnClickListener {
            if (!impoveStreaming) {
                counter++
                impoveStreaming = true
                binding.qrScannerBox.setImageResource(
                    R.drawable.baseline_check_box_fill
                )
            } else {
                counter--
                impoveStreaming = false
                binding.qrScannerBox.setImageResource(
                    R.drawable.baseline_check_box_outline_blank_24
                )
            }
        }
        binding.brCodeReaderBox.setOnClickListener {
            if (!onlineGame) {
                counter++
                onlineGame = true
                binding.brCodeReaderBox.setImageResource(
                    R.drawable.baseline_check_box_fill
                )
            } else {
                counter--
                onlineGame = false
                binding.brCodeReaderBox.setImageResource(
                    R.drawable.baseline_check_box_outline_blank_24
                )
            }
        }
        binding.qrCodeReaderBox.setOnClickListener {
            if (!stableData) {
                counter++
                stableData = true
                binding.qrCodeReaderBox.setImageResource(
                    R.drawable.baseline_check_box_fill
                )
            } else {
                counter--
                stableData = false
                binding.qrCodeReaderBox.setImageResource(
                    R.drawable.baseline_check_box_outline_blank_24
                )
            }
        }
        binding.brCodeMakerBox.setOnClickListener {
            if (!networkSwitch) {
                counter++
                networkSwitch = true
                binding.brCodeMakerBox.setImageResource(
                    R.drawable.baseline_check_box_fill
                )
            } else {
                counter--
                networkSwitch = false
                binding.brCodeMakerBox.setImageResource(
                    R.drawable.baseline_check_box_outline_blank_24
                )
            }
        }
        binding.qrMakerBox.setOnClickListener {
            if (!fastBrowser) {
                counter++
                fastBrowser = true
                binding.qrMakerBox.setImageResource(
                    R.drawable.baseline_check_box_fill
                )
            } else {
                counter--
                fastBrowser = false
                binding.qrMakerBox.setImageResource(
                    R.drawable.baseline_check_box_outline_blank_24
                )
            }
        }
    }
}