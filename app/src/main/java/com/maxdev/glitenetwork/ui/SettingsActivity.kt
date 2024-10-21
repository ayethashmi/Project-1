package com.maxdev.glitenetwork.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import com.maxdev.glitenetwork.BaseActivity
import com.maxdev.glitenetwork.R
import com.maxdev.glitenetwork.databinding.ActivitySettingsBinding
import com.maxdev.glitenetwork.features.newScreen
import com.maxdev.glitenetwork.utils.ConfigParam
import com.maxdev.glitenetwork.utils.SharedPrefObj
import com.maxdev.glitenetwork.utils.ConstantVariables
import com.maxdev.glitenetwork.utils.openUrlInBrowser

class SettingsActivity : BaseActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: ActivitySettingsBinding

    var fabVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        actionBar?.hide()
        sharedPreferences = getSharedPreferences("Myrehgffs", Context.MODE_PRIVATE)
        darkMode()
        toolBar()
        binding.appLangBtn.setOnClickListener {
            startActivity(
                Intent(
                    this, SelectLangActivity::class.java
                ).putExtra("fromSetting", true)
            )
        }
        binding.howtoUse.setOnClickListener {
            newScreen(HowtoUseActivity::class.java)
        }
        binding.clShare.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
                var shareMessage = "\nLet me recommend you this application\n\n"
                shareMessage =
                    """${shareMessage}https://play.google.com/store/apps/details?id=${packageName}""".trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                e.toString()
            }
        }
        binding.clRateUs.setOnClickListener { }
    }

    private fun toolBar() {
        binding.toolBar.backBtn.setOnClickListener {
            onBackPressed()
        }
        binding.toolBar.removeAdsbtn.visibility = View.GONE
        binding.toolBar.title.text = resources.getString(R.string.letSetting)
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {

            finish()

    }

    private fun changeLanguage() {
        val dialogView = layoutInflater.inflate(R.layout.language_alert_dialog, null)

        val dialog = AlertDialog.Builder(this).setView(dialogView).setCancelable(false).create()
        val englishLanLayout = dialogView.findViewById<ConstraintLayout>(R.id.defLangBtn)
        val hindiLanLayout = dialogView.findViewById<ConstraintLayout>(R.id.indianLangbtn)
        val chinseLanLayout = dialogView.findViewById<ConstraintLayout>(R.id.chineLangBtn)
        val itlianLanLayout = dialogView.findViewById<ConstraintLayout>(R.id.itlianBtn)
        val afrecianLanLayout = dialogView.findViewById<ConstraintLayout>(R.id.afrecianBtn)
        val spanishLanLayout = dialogView.findViewById<ConstraintLayout>(R.id.spanishBtn)
        val englishLanRR = dialogView.findViewById<ImageView>(R.id.defaulR)
        val hindiLanRR = dialogView.findViewById<ImageView>(R.id.indianLangR)
        val chinseLanRR = dialogView.findViewById<ImageView>(R.id.chineLanR)
        val itlianLanRR = dialogView.findViewById<ImageView>(R.id.itlianLaR)
        val spanishLanRR = dialogView.findViewById<ImageView>(R.id.spanishR)
        val afrecianLanRR = dialogView.findViewById<ImageView>(R.id.afrecianLanR)
        val dismis = dialogView.findViewById<ImageView>(R.id.doneBtn)
        dismis.setOnClickListener { dialog.dismiss() }
        dialog.show()
        val key = SharedPrefObj.getString(this, ConstantVariables.LANG_KEY)
        if (key == ConstantVariables.CHINISE) {
            englishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            hindiLanLayout.setBackgroundResource(R.drawable.strokeshape)
            chinseLanLayout.setBackgroundResource(R.drawable.strokeshapegreen)
            spanishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            afrecianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            itlianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            englishLanRR.setImageResource(R.drawable.nullc)
            hindiLanRR.setImageResource(R.drawable.nullc)
            chinseLanRR.setImageResource(R.drawable.radio)
            itlianLanRR.setImageResource(R.drawable.nullc)
            spanishLanRR.setImageResource(R.drawable.nullc)
            afrecianLanRR.setImageResource(R.drawable.nullc)
        } else if (key == ConstantVariables.HINDI) {
            englishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            hindiLanLayout.setBackgroundResource(R.drawable.strokeshapegreen)
            chinseLanLayout.setBackgroundResource(R.drawable.strokeshape)
            spanishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            afrecianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            itlianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            englishLanRR.setImageResource(R.drawable.nullc)
            hindiLanRR.setImageResource(R.drawable.radio)
            chinseLanRR.setImageResource(R.drawable.nullc)
            itlianLanRR.setImageResource(R.drawable.nullc)
            spanishLanRR.setImageResource(R.drawable.nullc)
            afrecianLanRR.setImageResource(R.drawable.nullc)
        } else if (key == ConstantVariables.SPANISH) {
            englishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            hindiLanLayout.setBackgroundResource(R.drawable.strokeshape)
            chinseLanLayout.setBackgroundResource(R.drawable.strokeshape)
            spanishLanLayout.setBackgroundResource(R.drawable.strokeshapegreen)
            afrecianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            itlianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            englishLanRR.setImageResource(R.drawable.nullc)
            hindiLanRR.setImageResource(R.drawable.nullc)
            chinseLanRR.setImageResource(R.drawable.nullc)
            itlianLanRR.setImageResource(R.drawable.nullc)
            spanishLanRR.setImageResource(R.drawable.radio)
            afrecianLanRR.setImageResource(R.drawable.nullc)
        } else if (key == ConstantVariables.AFRECIAN) {
            englishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            hindiLanLayout.setBackgroundResource(R.drawable.strokeshape)
            chinseLanLayout.setBackgroundResource(R.drawable.strokeshape)
            spanishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            afrecianLanLayout.setBackgroundResource(R.drawable.strokeshapegreen)
            itlianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            englishLanRR.setImageResource(R.drawable.nullc)
            hindiLanRR.setImageResource(R.drawable.nullc)
            chinseLanRR.setImageResource(R.drawable.nullc)
            itlianLanRR.setImageResource(R.drawable.nullc)
            spanishLanRR.setImageResource(R.drawable.nullc)
            afrecianLanRR.setImageResource(R.drawable.radio)
        } else if (key == ConstantVariables.ITLIAN) {
            englishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            hindiLanLayout.setBackgroundResource(R.drawable.strokeshape)
            chinseLanLayout.setBackgroundResource(R.drawable.strokeshape)
            spanishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            afrecianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            itlianLanLayout.setBackgroundResource(R.drawable.strokeshapegreen)
            englishLanRR.setImageResource(R.drawable.nullc)
            hindiLanRR.setImageResource(R.drawable.nullc)
            chinseLanRR.setImageResource(R.drawable.nullc)
            itlianLanRR.setImageResource(R.drawable.radio)
            spanishLanRR.setImageResource(R.drawable.nullc)
            afrecianLanRR.setImageResource(R.drawable.nullc)
        } else {
            englishLanLayout.setBackgroundResource(R.drawable.strokeshapegreen)
            hindiLanLayout.setBackgroundResource(R.drawable.strokeshape)
            chinseLanLayout.setBackgroundResource(R.drawable.strokeshape)
            spanishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            afrecianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            itlianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            englishLanRR.setImageResource(R.drawable.radio)
            hindiLanRR.setImageResource(R.drawable.nullc)
            chinseLanRR.setImageResource(R.drawable.nullc)
            itlianLanRR.setImageResource(R.drawable.nullc)
            spanishLanRR.setImageResource(R.drawable.nullc)
            afrecianLanRR.setImageResource(R.drawable.nullc)
        }
        englishLanLayout.setOnClickListener {
            englishLanLayout.setBackgroundResource(R.drawable.strokeshapegreen)
            hindiLanLayout.setBackgroundResource(R.drawable.strokeshape)
            chinseLanLayout.setBackgroundResource(R.drawable.strokeshape)
            spanishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            afrecianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            itlianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            SharedPrefObj.saveString(this, ConstantVariables.LANG_KEY, ConstantVariables.ENGLISH)
            englishLanRR.setImageResource(R.drawable.radio)
            hindiLanRR.setImageResource(R.drawable.nullc)
            chinseLanRR.setImageResource(R.drawable.nullc)
            setLanguage("en")
            dialog.dismiss()
        }

        hindiLanLayout.setOnClickListener {
            englishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            hindiLanLayout.setBackgroundResource(R.drawable.strokeshapegreen)
            chinseLanLayout.setBackgroundResource(R.drawable.strokeshape)
            spanishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            afrecianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            itlianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            SharedPrefObj.saveString(this, ConstantVariables.LANG_KEY, ConstantVariables.HINDI)
            hindiLanRR.setImageResource(R.drawable.radio)
            englishLanRR.setImageResource(R.drawable.nullc)
            chinseLanRR.setImageResource(R.drawable.nullc)
            setLanguage("hi")
            dialog.dismiss()
        }

        chinseLanLayout.setOnClickListener {
            englishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            hindiLanLayout.setBackgroundResource(R.drawable.strokeshape)
            chinseLanLayout.setBackgroundResource(R.drawable.strokeshapegreen)
            spanishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            afrecianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            itlianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            SharedPrefObj.saveString(this, ConstantVariables.LANG_KEY, ConstantVariables.CHINISE)
            chinseLanRR.setImageResource(R.drawable.radio)
            englishLanRR.setImageResource(R.drawable.nullc)
            hindiLanRR.setImageResource(R.drawable.nullc)
            setLanguage("cop") // The language code for Chinese should be 'zh'
            dialog.dismiss()
        }
        spanishLanLayout.setOnClickListener {
            englishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            hindiLanLayout.setBackgroundResource(R.drawable.strokeshape)
            chinseLanLayout.setBackgroundResource(R.drawable.strokeshape)
            spanishLanLayout.setBackgroundResource(R.drawable.strokeshapegreen)
            afrecianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            itlianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            SharedPrefObj.saveString(this, ConstantVariables.LANG_KEY, ConstantVariables.SPANISH)
            englishLanRR.setImageResource(R.drawable.nullc)
            hindiLanRR.setImageResource(R.drawable.nullc)
            chinseLanRR.setImageResource(R.drawable.nullc)
            itlianLanRR.setImageResource(R.drawable.nullc)
            spanishLanRR.setImageResource(R.drawable.nullc)
            afrecianLanRR.setImageResource(R.drawable.radio)
            setLanguage("es") // The language code for Chinese should be 'zh'
            dialog.dismiss()
        }
        itlianLanLayout.setOnClickListener {
            englishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            hindiLanLayout.setBackgroundResource(R.drawable.strokeshape)
            chinseLanLayout.setBackgroundResource(R.drawable.strokeshape)
            spanishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            afrecianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            itlianLanLayout.setBackgroundResource(R.drawable.strokeshapegreen)
            SharedPrefObj.saveString(this, ConstantVariables.LANG_KEY, ConstantVariables.ITLIAN)
            englishLanRR.setImageResource(R.drawable.nullc)
            hindiLanRR.setImageResource(R.drawable.nullc)
            chinseLanRR.setImageResource(R.drawable.nullc)
            itlianLanRR.setImageResource(R.drawable.radio)
            spanishLanRR.setImageResource(R.drawable.nullc)
            afrecianLanRR.setImageResource(R.drawable.nullc)
            setLanguage("it") // The language code for Chinese should be 'zh'
            dialog.dismiss()
        }
        afrecianLanLayout.setOnClickListener {
            englishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            hindiLanLayout.setBackgroundResource(R.drawable.strokeshape)
            chinseLanLayout.setBackgroundResource(R.drawable.strokeshape)
            spanishLanLayout.setBackgroundResource(R.drawable.strokeshape)
            afrecianLanLayout.setBackgroundResource(R.drawable.strokeshapegreen)
            itlianLanLayout.setBackgroundResource(R.drawable.strokeshape)
            SharedPrefObj.saveString(this, ConstantVariables.LANG_KEY, ConstantVariables.AFRECIAN)
            englishLanRR.setImageResource(R.drawable.nullc)
            hindiLanRR.setImageResource(R.drawable.nullc)
            chinseLanRR.setImageResource(R.drawable.nullc)
            itlianLanRR.setImageResource(R.drawable.nullc)
            spanishLanRR.setImageResource(R.drawable.nullc)
            afrecianLanRR.setImageResource(R.drawable.radio)
            setLanguage("af") // The language code for Chinese should be 'zh'
            dialog.dismiss()
        }

    }

    private fun darkMode() {
        val savedMode = sharedPreferences.getInt("MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        when (savedMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                binding.toolBar.title.setTextColor(resources.getColor(R.color.white))
                binding.dakrMTv.text = resources.getString(R.string.darkMode)
            }

            AppCompatDelegate.MODE_NIGHT_NO -> {
                binding.toolBar.title.setTextColor(resources.getColor(R.color.black))
                binding.dakrMTv.text = resources.getString(R.string.defaultMode)
            }

            else -> {
                binding.dakrMTv.text = resources.getString(R.string.defaultMode)
            }
        }
        binding.darkModeBtnik.setOnClickListener {
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

}