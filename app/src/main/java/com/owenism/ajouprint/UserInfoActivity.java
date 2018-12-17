package com.owenism.ajouprint;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class UserInfoActivity extends Activity {
    ImageView imgAjou;
    Button btnSubmit;
    EditText txtID;
    EditText txtName;
    public static String STUDENT_ID;
    public static String STUDENT_NAME;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        if(STUDENT_ID != null && STUDENT_NAME != null){
            startActivity(new Intent(UserInfoActivity.this, MainActivity.class));
            finish();
        }

        imgAjou = (ImageView) findViewById(R.id.ajou_logo);
        btnSubmit = (Button) findViewById(R.id.submit);
        txtID = (EditText)findViewById(R.id.inputId);
        txtName = (EditText)findViewById(R.id.inputName);
        imgAjou.setImageResource(R.drawable.ajou);


        btnSubmit.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                STUDENT_ID = txtID.getText().toString();
                STUDENT_NAME = txtName.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(UserInfoActivity.this);
                builder.setMessage("학번: "+STUDENT_ID + "\n이름: " + STUDENT_NAME + "\n제출하시겠습니까?");
                builder.setCancelable(true);
                builder.setTitle("사용자 정보");
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                    }
                });
                builder.setPositiveButton("예", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        startActivity(new Intent(UserInfoActivity.this, MainActivity.class));
                        finish();
                    }
                });
                builder.show();

            }
        });


    }
    public static String getStudentId(){
        return STUDENT_ID;
    }
    public static String getStudentName(){
        return STUDENT_NAME;
    }

}
