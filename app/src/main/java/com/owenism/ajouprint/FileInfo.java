package com.owenism.ajouprint;

import android.os.Parcel;
import android.os.Parcelable;

public class FileInfo implements Parcelable {

    private String absPath;
    private String fileName;
    private String input;

    public FileInfo(String absPath, String fileName){
        this.absPath = absPath;
        this.fileName = fileName;
        this.input = "";
    }

    public void setInput(int slideNum,
                         int copy, int border,
                         int id, String userName){
        this.input = this.fileName + " " + Integer.toString((int)(Math.log(slideNum)/Math.log(2)))
                + " " + Integer.toString(copy) + " " + Integer.toString(border) + " "
                + Integer.toString(id) + "_" + userName;
    }

    public String getInput(){
        return this.input;
    }
    public String getAbsPath(){
        return this.absPath;
    }
    public String getFileName(){
        return this.fileName;
    }
    // Parcel Part

    public FileInfo(Parcel in){
        String[] data = new String[3];
        in.readStringArray(data);
        this.absPath = data[0];
        this.fileName = data[1];
        this.input = data[2];
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeStringArray(new String[]{this.absPath, this.fileName, this.input});
    }

    public static final Parcelable.Creator<FileInfo> CREATOR = new Parcelable.Creator<FileInfo>(){
        @Override
        public FileInfo createFromParcel(Parcel source){
            return new FileInfo(source);
        }
        @Override
        public FileInfo[] newArray(int size){
            return new FileInfo[size];
        }
    };
}
