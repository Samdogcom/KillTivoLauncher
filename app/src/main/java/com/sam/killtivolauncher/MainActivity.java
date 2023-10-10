package com.sam.killtivolauncher;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

/**
 * ic_banner 要使用24bit的颜色,否则显示时会被放大
 *
 * @author Sam
 * @date 2023/10/11
 */
public class MainActivity extends Activity {

    private static final String LAUNCHER_PACKAGE_NAME = "com.google.android.tvlauncher";
    private static final String STAR_FIRE_PACKAGE_NAME = "com.aesq.zb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button kill = findViewById(R.id.kill);
        Button restart = findViewById(R.id.star);
        kill.requestFocus();

        kill.setOnClickListener(v -> {
            killBackgroundProcess(LAUNCHER_PACKAGE_NAME);
        });
        restart.setOnClickListener(v -> {
            launchApp(STAR_FIRE_PACKAGE_NAME);
        });
    }

    private void killBackgroundProcess(String packageName) {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        if (activityManager != null) {
            activityManager.killBackgroundProcesses(packageName);
        }
    }

    private void launchApp(String packageName) {
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntentForPackage != null) {
            startActivity(launchIntentForPackage);
        } else {
            Intent leanbackLaunchIntentForPackage = getPackageManager().getLeanbackLaunchIntentForPackage(packageName);
            if (leanbackLaunchIntentForPackage != null) {
                startActivity(leanbackLaunchIntentForPackage);
            } else {
                Toast.makeText(this, "App \"" + packageName + "\" not found!", Toast.LENGTH_LONG).show();
            }
        }
        finish();
    }
}