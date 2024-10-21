package com.maxdev.glitenetwork.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.maxdev.glitenetwork.BaseActivity;
import com.maxdev.glitenetwork.R;
import com.maxdev.glitenetwork.utils.ConfigParam;

public class AnimationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

    }

    public void GetStarted(View view) {
        startActivity(new Intent(AnimationActivity.this, MainActivity.class));
        finish();
    }
}