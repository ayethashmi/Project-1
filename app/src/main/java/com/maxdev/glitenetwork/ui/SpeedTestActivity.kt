package com.maxdev.glitenetwork.ui

import android.content.Context
import android.content.SharedPreferences
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.maxdev.glitenetwork.BaseActivity
import com.maxdev.glitenetwork.R
import com.maxdev.glitenetwork.databinding.ActivitySpeedTestBinding
import com.maxdev.glitenetwork.features.newScreen
import com.maxdev.glitenetwork.utils.ConfigParam

class SpeedTestActivity : BaseActivity() {
    lateinit var binding: ActivitySpeedTestBinding
    private var isFirstTime = true
    private var lastAdShownTime = 0L
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("Myrehgffs", Context.MODE_PRIVATE)
    }
    private var pendingAction: (() -> Unit)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySpeedTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar?.hide()
        goSeedTest()
        toolBar()
        darkMode()
    }
    private fun darkMode() {
        val savedMode = sharedPreferences.getInt("MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        when (savedMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                binding.toolBar.title.setTextColor(resources.getColor(R.color.white))
                binding.textView2.setTextColor(resources.getColor(R.color.white))
                binding.serverTxt.setTextColor(resources.getColor(R.color.white))
                binding.ipTxt.setTextColor(resources.getColor(R.color.white))
                binding.serverImg.setColorFilter(getColor(R.color.white), PorterDuff.Mode.SRC_IN)
                binding.ipImg.setColorFilter(getColor(R.color.white), PorterDuff.Mode.SRC_IN)

            }
            AppCompatDelegate.MODE_NIGHT_NO -> {
                binding.textView2.setTextColor(resources.getColor(R.color.black))
                binding.toolBar.title.setTextColor(resources.getColor(R.color.black))
                binding.serverTxt.setTextColor(resources.getColor(R.color.black))
                binding.ipTxt.setTextColor(resources.getColor(R.color.black))
                binding.serverImg.setColorFilter(getColor(R.color.black), PorterDuff.Mode.SRC_IN)
                binding.ipImg.setColorFilter(getColor(R.color.black), PorterDuff.Mode.SRC_IN)
            }
            else -> {
            }
        }
    }

    private fun toolBar() {

        binding.toolBar.removeAdsbtn.visibility=View.GONE
        binding.toolBar.backBtn.setOnClickListener { onBackPressed() }
    }

    private fun goSeedTest() {
        binding.textView.setOnClickListener {
            binding.textView.visibility= View.GONE
            binding.textView2.visibility= View.VISIBLE
            binding.progressBar.visibility= View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                // Make the TextView visible after 3 seconds

                 newScreen(NetSpeedActivity::class.java)
                 binding.textView.visibility= View.VISIBLE
                 binding.textView2.visibility= View.INVISIBLE
                 binding.progressBar.visibility= View.INVISIBLE

            }, 2000)
        }
    }

}