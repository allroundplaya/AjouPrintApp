package com.owenism.ajouprint;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashActivity extends Activity {
    private static final int PERMISSION_REQUEST_CODE = 0x100;
    ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        imgLogo = (ImageView) findViewById(R.id.imageView);
        imgLogo.setImageResource(R.drawable.splash);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1500);
                }
                catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                finally {
                    if (checkPermission()){
//                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        startActivity(new Intent(SplashActivity.this, UserInfoActivity.class));
                        finish();
                    }
                    else{
                        ActivityCompat.requestPermissions(SplashActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                    }
                }
            }
        };
        timer.start();
    }

    private boolean checkPermission(){
        if (Build.VERSION.SDK_INT < 23) return true;
        int result = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                Intent i = new Intent(SplashActivity.this, UserInfoActivity.class);
                startActivity(i);
                finish();
            }else{
                finish();
                System.exit(0);
            }
        }
    }

}
