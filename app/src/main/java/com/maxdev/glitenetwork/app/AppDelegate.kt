package com.maxdev.glitenetwork.app

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.akexorcist.localizationactivity.ui.LocalizationApplication
import com.maxdev.glitenetwork.BuildConfig
import java.util.Locale

class AppDelegate : LocalizationApplication(), ActivityLifecycleCallbacks,
    DefaultLifecycleObserver {
    private lateinit var sharedPreferences: SharedPreferences
    private var currentActivity: Activity? = null

    override fun getDefaultLanguage(context: Context): Locale {
        return Locale.ENGLISH
    }

    override fun onCreate() {
        super<LocalizationApplication>.onCreate()
        appClassIns = this
        registerActivityLifecycleCallbacks(this)
        sharedPreferences = getSharedPreferences("Myrehgffs", Context.MODE_PRIVATE)
        val savedMode = if (sharedPreferences.contains("MODE")) {
            sharedPreferences.getInt("MODE", AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(savedMode)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        initConfig()
        setNotificationChannels()
    }

    private fun setNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannels(
                listOf(
                    NotificationChannel(
                        "FCM",
                        "App Notification",
                        NotificationManager.IMPORTANCE_HIGH
                    )
                )
            )
        }
    }

    private fun initConfig() {
        val time = if (BuildConfig.DEBUG) 0L
        else 3600L


    }

    override fun onStart(owner: LifecycleOwner) {
        super<DefaultLifecycleObserver>.onStart(owner)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {}


    companion object {
        @SuppressLint("StaticFieldLeak")
        var appClassIns: AppDelegate? = null

    }
}
