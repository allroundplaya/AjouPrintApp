package com.owenism.ajouprint;

import android.os.Parcel;
import android.os.Parcelable;

public class FileInfo implements Parcelable {
    private String input;
    private String absPath;

    public FileInfo(String absPath){
        this.absPath = absPath;
    }

    public void setInput(String fileName, int slideNum,
                         int copy, int border,
                         int id, String userName){
        this.input = fileName + " " + Integer.toString((int)(Math.log(slideNum)/Math.log(2)))
                + " " + Integer.toString(copy) + " " + Integer.toString(border) + " "
                + Integer.toString(id) + "_" + userName;

    }

    public String getInput(){
        return this.input;
    }
    public String getAbsPath(){
        return this.absPath;
    }

    // Parcel Part

    public FileInfo(Parcel in){
        String[] data = new String[2];
        in.readStringArray(data);
        this.input = data[0];
        this.absPath = data[1];
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeStringArray(new String[]{this.input, this.absPath});

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
