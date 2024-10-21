package com.maxdev.glitenetwork.ui

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.maxdev.glitenetwork.BaseActivity
import com.maxdev.glitenetwork.R
import com.maxdev.glitenetwork.databinding.ActivityNetSpeedBinding
import com.maxdev.glitenetwork.utils.ConfigParam
import com.maxdev.glitenetwork.utils.GetSpeedTestHostsHandler
import com.maxdev.glitenetwork.utils.HttpDownloadTest
import com.maxdev.glitenetwork.utils.HttpUploadTest

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.text.DecimalFormat
import java.util.ArrayList
import java.util.HashSet

class NetSpeedActivity : BaseActivity(), EasyPermissions.PermissionCallbacks {
    lateinit var binding: ActivityNetSpeedBinding

//    // Declare a Thread object at the class level
//    private var speedTestThread: Thread? = null

    var getSpeedTestHostsHandler: GetSpeedTestHostsHandler? = null
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("Myrehgffs", Context.MODE_PRIVATE)
    }
    lateinit var dec: DecimalFormat
    var tempBlackList: HashSet<String>? = null
    var downloadTest: HttpDownloadTest? = null
    var uploadTest: HttpUploadTest? = null
    private lateinit var handler: Handler
    lateinit var startButton: Button
    var perms = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
    var isStoped = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetSpeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolBar.backBtn.setOnClickListener { onBackPressed() }
        binding.toolBar.removeAdsbtn.visibility = View.GONE
        handler = Handler(Looper.getMainLooper()) // Initialize the handler

        binding.pointerSpeedometer.withTremble = false
        testSpeed()
        darkMode()
    }

    fun testSpeed() {
        dec = DecimalFormat("#.##")
        tempBlackList = HashSet()

        getSpeedTestHostsHandler = GetSpeedTestHostsHandler()
        getSpeedTestHostsHandler!!.start()
        startButton = findViewById<Button>(R.id.btn_start)

        startButton.setOnClickListener(View.OnClickListener {
            getSpeedTestHostsHandler = GetSpeedTestHostsHandler()
            val pingRateList: MutableList<Double> = ArrayList()
            val downloadRateList: MutableList<Double> = ArrayList()
            val uploadRateList: MutableList<Double> = ArrayList()
            var pingTestStarted = false
            var pingTestFinished = false
            var downloadTestStarted = false
            var downloadTestFinished = false
            var uploadTestStarted = false
            var uploadTestFinished = false
            isStoped = false

            if (startButton.text == getString(R.string.start) || startButton.text == getString(R.string.restart_test)) {
                if (!EasyPermissions.hasPermissions(this, *perms)) {
                    EasyPermissions.requestPermissions(
                        this,
                        "We need Location permission to perform this action",
                        123,
                        *perms
                    );
                    return@OnClickListener
                }
                startButton.isEnabled = false
//                startButton.text = "Processing..."
                startButton.text = getString(R.string.stop)
                getSpeedTestHostsHandler?.start()
                Thread(object : Runnable {
                    var rotate: RotateAnimation? = null
                    override fun run() {
                        var timeCount = 600 //1min
                        while (getSpeedTestHostsHandler != null && !getSpeedTestHostsHandler!!.isFinished) {
                            timeCount--
                            try {
                                Thread.sleep(100)
                            } catch (e: InterruptedException) {
                            }
                            if (timeCount <= 0) {
                                runOnUiThread {
                                    Toast.makeText(
                                        applicationContext,
                                        "No Connection...",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    startButton.isEnabled = true
                                    startButton.textSize = 16f
                                    startButton.text = getString(R.string.restart_test)
                                }
                                getSpeedTestHostsHandler = null
                                return
                            }
                        }

                        //Find closest server
                        getSpeedTestHostsHandler?.let {

                            val mapKey = getSpeedTestHostsHandler!!.mapKey
                            val mapValue = getSpeedTestHostsHandler!!.mapValue
                            val selfLat = getSpeedTestHostsHandler!!.selfLat
                            val selfLon = getSpeedTestHostsHandler!!.selfLon
                            var tmp = 19349458.0
                            var dist = 0.0
                            var findServerIndex = 0
                            for (index in mapKey.keys) {
                                if (tempBlackList!!.contains(mapValue[index]!![5])) {
                                    continue
                                }
                                val source = Location("Source")
                                source.latitude = selfLat
                                source.longitude = selfLon
                                val ls = mapValue[index]!!
                                val dest = Location("Dest")
                                dest.latitude = ls[0].toDouble()
                                dest.longitude = ls[1].toDouble()
                                val distance = source.distanceTo(dest).toDouble()
                                if (tmp > distance) {
                                    tmp = distance
                                    dist = distance
                                    findServerIndex = index
                                }
                            }
                            val testAddr = mapKey[findServerIndex]!!.replace("http://", "https://")
                            val info = mapValue[findServerIndex]
                            if (info == null) {
                                runOnUiThread {
                                    startButton.textSize = 12f
                                    startButton.text =
                                        "There was a problem in getting Host Location. Try again later."
                                }
                                return
                            }

                            //Init Test

                            downloadTest = HttpDownloadTest(
                                testAddr.replace(
                                    testAddr.split("/").toTypedArray()[testAddr.split("/")
                                        .toTypedArray().size - 1], ""
                                )
                            )
                            Log.d("TAG", "run-abc: $testAddr")
                            uploadTest = HttpUploadTest(testAddr)

                            //Tests
                            while (!isStoped) {

                                if (!downloadTestStarted) {
                                    runOnUiThread {
                                        binding.incomingTv.text = getString(
                                                R.string.mbps,
                                                dec.format(downloadTest?.finalDownloadRate)
                                                    .toString()
                                            )
                                    }

                                    downloadTest?.start()
                                    downloadTestStarted = true
                                }
                                if (downloadTestFinished && !uploadTestStarted) {
                                    uploadTest?.start()
                                    uploadTestStarted = true
                                }

                                //Download Test
                                if (downloadTest != null && downloadTestFinished) {
                                    //Failure
                                    if (downloadTest?.finalDownloadRate == 0.0) {
                                        println("Download error...")
                                    } else {

                                        //Success
                                        runOnUiThread {
                                            binding.incomingTv.text =
                                                getString(
                                                    R.string.mbps,
                                                    dec.format(downloadTest?.finalDownloadRate)
                                                        .toString()
                                                )

                                        }
                                    }
                                } else {
                                    //Calc position
                                    val downloadRate: Double? =
                                        downloadTest?.instantDownloadRate
                                    if (downloadRate != null) {
                                        downloadRateList.add(downloadRate)
                                        position =
                                            getPositionByRate(downloadRate)
                                    }

                                    runOnUiThread {
                                        rotate = RotateAnimation(
                                            lastPosition.toFloat(),
                                            position.toFloat(),
                                            Animation.RELATIVE_TO_SELF,
                                            0.5f,
                                            Animation.RELATIVE_TO_SELF,
                                            0.5f
                                        )
                                        rotate!!.interpolator = LinearInterpolator()
                                        rotate!!.duration = 100
                                        downloadTest?.instantDownloadRate?.let {
                                            binding.pointerSpeedometer.setSpeedAt((it / 100 * 100).toFloat())
                                        }
                                        //barImageView.startAnimation(rotate)
                                        binding.incomingTv.text =
                                            getString(
                                                R.string.mbps,
                                                dec.format(downloadTest?.instantDownloadRate)
                                                    .toString()
                                            )
                                        downloadTest?.let {

                                        }

                                    }
                                    lastPosition = position
                                }

                                //Upload Test
                                if (downloadTestFinished) {
                                    if (uploadTestFinished) {
                                        //Failure
                                        if (uploadTest?.finalUploadRate == 0.0) {
                                            println("Upload error...")
                                        } else {
                                            //Success
                                            runOnUiThread {
                                                binding.outgoingTv.text =
                                                    getString(
                                                        R.string.mbps,
                                                        dec.format(uploadTest?.finalUploadRate)
                                                            .toString()
                                                    )
                                            }
                                        }
                                    } else {
                                        //Calc position
                                        val uploadRate: Double? = uploadTest?.instantUploadRate
                                        if (uploadRate != null) {
                                            uploadRateList.add(uploadRate)
                                            position =
                                                getPositionByRate(uploadRate)
                                        }
                                        runOnUiThread {
                                            rotate = RotateAnimation(
                                                lastPosition.toFloat(),
                                                position.toFloat(),
                                                Animation.RELATIVE_TO_SELF,
                                                0.5f,
                                                Animation.RELATIVE_TO_SELF,
                                                0.5f
                                            )
                                            rotate!!.interpolator = LinearInterpolator()
                                            rotate!!.duration = 100
                                            uploadTest?.instantUploadRate?.let {
                                                binding.pointerSpeedometer.setSpeedAt((it / 100 * 100).toFloat())
                                            }
                                            // barImageView.startAnimation(rotate)
                                            binding.outgoingTv.text =
                                                dec.format(uploadTest?.instantUploadRate)
                                                    .toString()
                                        }
                                        lastPosition = position

                                    }
                                }

                                //Test bitti
                                if (downloadTestFinished && uploadTest?.isFinished == true) {
                                    if (uploadTest?.finalUploadRate == 0.0) {
                                        println("Upload error...")
                                    } else {
                                        //Success
                                        CoroutineScope(Dispatchers.Main).launch {
                                            binding.outgoingTv.text =
                                                dec.format(uploadTest?.finalUploadRate)
                                                    .toString()


                                            if (!isStoped)
                                                runOnUiThread {
                                                    isStoped = true
                                                    startButton.isEnabled = true
                                                    startButton.textSize = 16f
                                                    startButton.text =
                                                        getString(R.string.restart_test)
                                                    pingTestFinished = true
                                                    downloadTestFinished = true
                                                    uploadTestFinished = true
                                                    binding.pointerSpeedometer.setSpeedAt(0f)
                                                }

                                        }
                                    }
                                    break
                                }
                                if (!isStoped) {
                                    if (downloadTest?.isFinished == true) {
                                        downloadTestFinished = true
                                    }
                                    if (uploadTest?.isFinished == true) {
                                        uploadTestFinished = true
                                        if (uploadTest?.finalUploadRate == 0.0) {
                                            println("Upload error...")
                                        } else {
                                            //Success
                                            runOnUiThread {
                                                binding.outgoingTv.text =
                                                    getString(
                                                        R.string.mbps,
                                                        dec.format(uploadTest?.finalUploadRate)
                                                            .toString()
                                                    )
                                            }
                                        }


                                    }

                                    try {
                                        Thread.sleep(100)
                                    } catch (e: InterruptedException) {
                                    }

                                }

                            }
                        }

// Enable the button after speed testing is completed
                        runOnUiThread {
                            startButton.isEnabled = true
                        }

                    }
                }).start()
            } else {
                isStoped = true
                pingTestFinished = true
                downloadTestFinished = true
                uploadTestFinished = true
                startButton.text = "Start"

            }

        })
    }

    private fun darkMode() {
        val savedMode = sharedPreferences.getInt("MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        when (savedMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                binding.toolBar.title.setTextColor(resources.getColor(R.color.white))
            }

            AppCompatDelegate.MODE_NIGHT_NO -> {
                binding.toolBar.title.setTextColor(resources.getColor(R.color.black))
            }

            else -> {
            }
        }

    }

    fun getPositionByRate(rate: Double): Int {
        if (rate <= 1) {
            return (rate * 30).toInt()
        } else if (rate <= 10) {
            return (rate * 6).toInt() + 30
        } else if (rate <= 30) {
            return ((rate - 10) * 3).toInt() + 90
        } else if (rate <= 50) {
            return ((rate - 30) * 1.5).toInt() + 150
        } else if (rate <= 100) {
            return ((rate - 50) * 1.2).toInt() + 180
        }
        return 0
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    companion object {
        var position = 0
        var lastPosition = 0
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        startButton.performClick()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
