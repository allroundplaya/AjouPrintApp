package com.owenism.ajouprint;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    String curDir; // current dir 문자열
    String rootDir; // root dir 문자열
    TextView mCurrentTxt;
    ListView mFileList;
//    ArrayAdapter<String> mAdapter;

//    ArrayList<String> arrFileName; // File 이름의 ArrayList 아마 필요없을 것으로 예상됨.
    ArrayList<File> arrFile

    Button btnFileLoad; // 파일 불러오기
//    FileList fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fl.onCreate(savedInstanceState);
        setTitle("버튼 테스트");
        String path = Environment.getExternalStorageDirectory().toString()+"/Download";
        Toast.makeText(this, path, Toast.LENGTH_SHORT).show();

        setContentView(R.layout.file_selection);

        btnFileLoad = findViewById(R.id.file_load);



        btnFileLoad.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View arg0, MotionEvent arg1){
                Toast.makeText(MainActivity.this, "File Loading...", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }

}









