package com.maxdev.glitenetwork.utils

import android.content.Context
import android.content.Intent

object NewScreen {

    fun start(context: Context,c:Class<*>){
        context.startActivity(Intent(context,c))
    }
}