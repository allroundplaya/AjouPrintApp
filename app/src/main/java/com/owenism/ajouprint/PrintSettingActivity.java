package com.owenism.ajouprint;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class PrintSettingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.print_setting);
        Toast.makeText(this, "Print Setting Layout", Toast.LENGTH_SHORT).show();

    }
}
