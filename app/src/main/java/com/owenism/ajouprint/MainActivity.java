package com.owenism.ajouprint;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ListActivity {

    List<String> item = null;
    List<String> path = null;
    String rootDir =  Environment.getExternalStorageDirectory().getAbsolutePath(); // root dir 문자열
    String curDir;

    TextView mCurDir;
    ListView mFileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE}, MODE_PRIVATE);
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mCurDir = (TextView) findViewById(R.id.tv_path);
        this.mFileList = (ListView) findViewById(android.R.id.list);

        getDir(this.rootDir);
    }

    private void getDir(String path){
        this.curDir = path;
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE}, MODE_PRIVATE);
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        this.mCurDir.setText("현재위치: " + path);
        this.item = new ArrayList<String>();
        this.path = new ArrayList<String>();
        File f = new File(path);
        File[] files = f.listFiles();

        if(!path.equals(this.rootDir)){
            this.item.add(this.rootDir);
            this.path.add(this.rootDir);
            this.item.add("../");
            this.path.add(f.getParent());
        }

        if(files != null) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                this.path.add(file.getPath());
                if (file.isDirectory()) this.item.add(file.getName() + "/");
                else item.add(file.getName());
            }
        }

        ArrayAdapter<String> fileList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.item);
        this.mFileList.setAdapter(fileList);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){

        final File file = new File(path.get(position));

//        Toast.makeText(this, this.curDir, Toast.LENGTH_SHORT).show();


        if(file.isDirectory()) {
            if (file.canRead()) {
                getDir(path.get(position));

            }
            else {
                new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.hung)
                        .setTitle("[" + file.getName() + "] folder can't be read!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                            }
                        }).show();
            }
        }

        else{
//            new AlertDialog.Builder(this)
//                    .setIcon(R.mipmap.ic_launcher)
//                    .setTitle("["+ file.getName() + "]")
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener(){
//                        public void onClick(DialogInterface dialog, int which){
//                            //TODO Auto-generated method stub
//                        }
//                    }).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(file.getName() + "을(를) 선택하시겠습니까?");
            builder.setCancelable(true);
            builder.setTitle("파일 선택");
            builder.setNegativeButton("아니오", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){
                    Toast.makeText(MainActivity.this, "아무일도 안일어나는지 테스트 중입니다.", Toast.LENGTH_SHORT).show();

                }
            });
            builder.setPositiveButton("예", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){
                    Toast.makeText(MainActivity.this, file.getName()+" 테스트 중입니다.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext() , PrintSettingActivity.class));
                }

            });
            builder.show();
        }

    }

    @Override
    public void onBackPressed(){

        final File file = new File(this.curDir);
            if (this.curDir.equals(this.rootDir)){
                super.onBackPressed();
            }
            else{
                getDir(file.getParent());
                Toast.makeText(this, "curDir: "+this.curDir+"\nrootDir: " +this.rootDir, Toast.LENGTH_SHORT).show();
            }


    }

}











