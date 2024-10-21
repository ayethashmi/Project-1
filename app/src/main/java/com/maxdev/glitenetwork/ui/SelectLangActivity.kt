package com.maxdev.glitenetwork.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import com.maxdev.glitenetwork.BaseActivity
import com.maxdev.glitenetwork.R
import com.maxdev.glitenetwork.databinding.ActivitySelectLangBinding
import com.maxdev.glitenetwork.features.newScreen
import com.maxdev.glitenetwork.utils.ConfigParam
import com.maxdev.glitenetwork.utils.SharedPrefObj
import com.maxdev.glitenetwork.utils.ConstantVariables

class SelectLangActivity : BaseActivity() {
    var seletedLang = "en"
    lateinit var binding: ActivitySelectLangBinding

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("Myrehgffs", Context.MODE_PRIVATE)
    }

    var fromSetting = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectLangBinding.inflate(layoutInflater)
        setContentView(binding.root)


        selectLanguage()
        toolbar()
        checkLang()
        selectLang(seletedLang)
        darkMode()
        fromSetting = intent.getBooleanExtra("fromSetting", false)
    }

    private fun darkMode() {
        val savedMode = sharedPreferences.getInt("MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        when (savedMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
            }

            AppCompatDelegate.MODE_NIGHT_NO -> {
            }
        }
    }

    private fun checkLang() {
        val key = SharedPrefObj.getString(this, ConstantVariables.LANG_KEY)
        if (key == ConstantVariables.HINDI) {
            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.radio)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
        } else if (key == ConstantVariables.CHINISE) {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)

            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.radio)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
        } else if (key == ConstantVariables.SPANISH) {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)

            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.radio)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
        } else if (key == ConstantVariables.AFRECIAN) {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)
            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.radio)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
        } else if (key == ConstantVariables.ITLIAN) {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)
            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.radio)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
        } else if (key == ConstantVariables.KOREAN) {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)
            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.radio)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
        } else if (key == ConstantVariables.VIETNAMESE) {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)
            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.radio)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
        } else if (key == ConstantVariables.JAPANESE) {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)
            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.radio)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
        } else if (key == ConstantVariables.MALAY) {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)
            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.radio)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
        } else if (key == ConstantVariables.INDONESIAN) {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshapegreen)
            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.radio)
        } else {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)
            binding.defaulR.setImageResource(R.drawable.radio)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
        }
    }

    private fun selectLanguage() {
        binding.defLangBtn.setOnClickListener {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)
            binding.defaulR.setImageResource(R.drawable.radio)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
            selectLang(ConstantVariables.ENGLISH)
        }

        binding.indianLangbtn.setOnClickListener {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)

            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.radio)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
            selectLang(ConstantVariables.HINDI)
        }

        binding.chineLangBtn.setOnClickListener {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)

            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.radio)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
            selectLang(ConstantVariables.CHINISE)
        }

        binding.spanishBtn.setOnClickListener {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)

            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.radio)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
            selectLang(ConstantVariables.SPANISH)
        }

        binding.itlianBtn.setOnClickListener {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)
            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.radio)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
            selectLang(ConstantVariables.ITLIAN)
        }

        binding.afrecianBtn.setOnClickListener {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)
            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.radio)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
            selectLang(ConstantVariables.AFRECIAN)
        }

        binding.clKorean.setOnClickListener {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)
            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.radio)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
            selectLang(ConstantVariables.KOREAN)
        }

        binding.clVietnamese.setOnClickListener {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)
            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.radio)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
            selectLang(ConstantVariables.VIETNAMESE)
        }

        binding.clJapanese.setOnClickListener {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)
            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.radio)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
            selectLang(ConstantVariables.JAPANESE)
        }

        binding.clMalay.setOnClickListener {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshapegreen)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshape)
            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.radio)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.nullc)
            selectLang(ConstantVariables.MALAY)
        }

        binding.clIndonesianBtn.setOnClickListener {
//            binding.defLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.indianLangbtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.chineLangBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.spanishBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.afrecianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.itlianBtn.setBackgroundResource(R.drawable.strokeshape)
//            binding.clKorean.setBackgroundResource(R.drawable.strokeshape)
//            binding.clJapanese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clMalay.setBackgroundResource(R.drawable.strokeshape)
//            binding.clVietnamese.setBackgroundResource(R.drawable.strokeshape)
//            binding.clIndonesianBtn.setBackgroundResource(R.drawable.strokeshapegreen)
            binding.defaulR.setImageResource(R.drawable.nullc)
            binding.indianLangR.setImageResource(R.drawable.nullc)
            binding.chineLanR.setImageResource(R.drawable.nullc)
            binding.spanishR.setImageResource(R.drawable.nullc)
            binding.itlianLaR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.afrecianLanR.setImageResource(R.drawable.nullc)
            binding.ivKoreanRadio.setImageResource(R.drawable.nullc)
            binding.ivJapaneseRadio.setImageResource(R.drawable.nullc)
            binding.ivMalayRadio.setImageResource(R.drawable.nullc)
            binding.ivVietnameseRadio.setImageResource(R.drawable.nullc)
            binding.ivIndonesian.setImageResource(R.drawable.radio)
            selectLang(ConstantVariables.INDONESIAN)
        }
    }

//    private fun loadNativeAd() {
//        try {
//            AppDelegate.nativeAd?.apply {
//                NativeAdPair(this).populate(
//                    this@SelectLangActivity,
//                    R.layout.ad_unified_banner,
//                    binding.adContainer
//                )
//                (application as AppDelegate).loadAdNativeBottomMain()
//            } ?: run {
//                loadNativeAds(
//                    binding.adContainer,
//                    R.layout.ad_unified_banner,
//                    ADUnitPlacements.NATIVE_AD
//                )
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
////        BannerAdsManager(this).loadCollapseBanner(binding.bannerAdView)
//    }

//    private fun loadNativeAd() {
//        try {
//            AppDelegate.nativeAd?.apply {
//                NativeAdPair(this).populate(
//                    this@SelectLangActivity,
//                    R.layout.ad_unified_native,
//                    binding.adContainer
//                )
//                (application as AppDelegate).loadAdNativeBottomMain()
//            } ?: run {
//                loadNativeAds(
//                    binding.adContainer,
//                    R.layout.ad_unified_native,
//                    ADUnitPlacements.NATIVE_AD
//                )
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

    private fun selectLang(lang: String) {
        seletedLang = lang
        binding.toolBar.removeAdsbtn.setOnClickListener {
            SharedPrefObj.saveString(this, ConstantVariables.LANG_KEY, seletedLang)
            setLanguage(seletedLang)
            if (fromSetting) {
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )

            } else {
                newScreen(BoardingActivity::class.java)
                finish()


            }
        }
    }

    private fun toolbar() {
        val typeface =
            ResourcesCompat.getFont(this, R.font.poppins_bold) // replace with your font resource
        binding.toolBar.title.typeface = typeface
        binding.toolBar.removeAdsbtn.setImageResource(R.drawable.check_circle_language)
        binding.toolBar.backBtn.visibility = View.GONE
        binding.toolBar.title.text = resources.getString(R.string.settLangT)

    }
}