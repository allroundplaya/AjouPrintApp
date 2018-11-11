package com.owenism.ajouprint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class PrintSettingActivity extends Activity {

    private String direction = "가로";
    private String slideNum = "1";
    private String edgeCheck = "포함";

    ImageView img;

    protected void showLayout(ImageView img){
        String temp = this.direction + this.slideNum;

        switch(temp){
            case "가로1":
                img.setImageResource(R.drawable.horizontal_1);
                break;
            case "가로2":
                img.setImageResource(R.drawable.horizontal_2);
                break;
            case "가로4":
                img.setImageResource(R.drawable.horizontal_4);
                break;
            case "세로1":
                img.setImageResource(R.drawable.vertical_1);
                break;
            case "세로2":
                img.setImageResource(R.drawable.vertical_2);
                break;
            case "세로4":
                img.setImageResource(R.drawable.vertical_4);
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.print_setting);
        Toast.makeText(this, "Print Setting Layout", Toast.LENGTH_SHORT).show();

        final String[] direction = {"가로", "세로"};
        final String[] slides = {"1", "2", "4"};
        final String[] edge = {"포함", "미포함"};
        final Integer[] copy = {1, 2, 3, 4};

        img= (ImageView) findViewById(R.id.imageView);

        Spinner spinDirection = (Spinner) findViewById(R.id.spinner_direction);
        Spinner spinSlide = (Spinner) findViewById(R.id.spinner_slide);
        Spinner spinEdge = (Spinner) findViewById(R.id.spinner_edge);
        Spinner spinCopy = (Spinner) findViewById(R.id.spinner_copy);


//        showLayout(img);
        img.setImageResource(R.drawable.horizontal_1);
        ArrayAdapter<String> adapterDirection = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, direction);
        spinDirection.setAdapter(adapterDirection);
        ArrayAdapter<String> adapterSlide = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, slides);
        spinSlide.setAdapter(adapterSlide);
        ArrayAdapter<String> adapterEdge = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, edge);
        spinEdge.setAdapter(adapterEdge);
        ArrayAdapter<Integer> adapterCopy = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, copy);
        spinCopy.setAdapter(adapterCopy);

        spinDirection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PrintSettingActivity.this.direction = (String)parent.getSelectedItem();
//                Toast.makeText(PrintSettingActivity.this, PrintSettingActivity.this.direction, Toast.LENGTH_SHORT).show();
                showLayout(img);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                PrintSettingActivity.this.direction = "가로";
            }
        });

        spinSlide.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    PrintSettingActivity.this.slideNum = (String)parent.getSelectedItem();
//                    Toast.makeText(PrintSettingActivity.this, PrintSettingActivity.this.slideNum, Toast.LENGTH_SHORT).show();
                    showLayout(img);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
//                    PrintSettingActivity.this.slideNum = "1";
                }
        });

        spinEdge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch((String)parent.getSelectedItem()){
                    case "포함":
                        PrintSettingActivity.this.edgeCheck = "포함";
                        showLayout(img);
//                        Toast.makeText(PrintSettingActivity.this, "포함"+PrintSettingActivity.this.edgeCheck, Toast.LENGTH_SHORT).show();
                        break;
                    case "미포함":
                        PrintSettingActivity.this.edgeCheck = "미포함";
                        showLayout(img);
//                        Toast.makeText(PrintSettingActivity.this, "미포함"+PrintSettingActivity.this.edgeCheck, Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



}
