package com.maxdev.glitenetwork.features

import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.showCustomToast(m:String){
    Toast.makeText(this, m, Toast.LENGTH_SHORT).show()
}

fun Context.newScreen(c:Class<*>){
    startActivity(Intent(this,c))
}



