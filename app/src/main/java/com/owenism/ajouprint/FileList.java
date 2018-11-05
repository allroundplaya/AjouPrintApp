//package com.owenism.ajouprint;
//
//import android.app.ListActivity;
//import android.os.Bundle;
//import android.os.Environment;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//import java.lang.Object;
//
//public class FileList extends ListActivity {
//    private File file;
//    private List myList;
//
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//
//        myList = new ArrayList();
//
//        String rootSD = Environment.getExternalStorageDirectory().toString();
//        file = new File(rootSD + "/Download"); // 경로".../Download"
//
//        File list[] = file.listFiles(); // ".../Download"에 담긴 File 들을 담은 File array 선언
//
//        // myList 에 File(array)의 element 들의 이름을 append.
//        for(int i = 0 ; i < list.length ; i++){
//            myList.add(list[i].getName());
//        }
//
//        setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, myList));
//
//    }
//
//    protected void onListenItemClick(ListView l, View v, int position, long id){
//        super.onListItemClick(l, v, position, id);
//
//        File temp_file = new File(file, myList.get(position).toString());
//
//        if(!temp_file.isFile()){
//            file = new File(file, myList.get(position).toString());
//            File list[] = file.listFiles();
//
//            myList.clear();
//
//            for(int i = 0 ;i<list.length; i++){
//                myList.add(list[i].getName());
//            }
//            Toast.makeText(getApplicationContext(), file.toString(), Toast.LENGTH_LONG).show();
//            setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, myList));
//        }
//    }
//
//    @Override
//    public void onBackPressed(){
//        String parent = file.getParent().toString();
//        file = new File(parent);
//        File list[] = file.listFiles();
//
//        myList.clear();
//
//        for(int i = 0 ; i < list.length ; i++){
//            myList.add(list[i].getName());
//        }
//        Toast.makeText(getApplicationContext(), parent, Toast.LENGTH_SHORT).show();
//        setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, myList));
//    }
//}
