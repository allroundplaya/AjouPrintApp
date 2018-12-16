package com.owenism.ajouprint;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    public final static String SERVER_ADDR = "192.168.22.190"; //蹂�寃� �븘�슂

    public Client(String absPath, String printOpt){
        Socket socket = null;

        try {
            socket = new Socket(SERVER_ADDR, 5000); // socket(),connect();
            System.out.println("Successfully connected to server.");

            FileSender fs = new FileSender(socket, absPath, printOpt);
            fs.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class FileSender extends Thread {
    Socket socket;

    File f;
    DataOutputStream dos;
    FileInputStream fis;
    BufferedInputStream bis;
    int control = 0;

    OutputStreamWriter osw;
    BufferedWriter bw;
    String printOpt; // ex: "true 1 5"

    public FileSender(Socket socket, String filepath, String printOpt) {
        this.socket = socket;
        f = new File(filepath);
        this.printOpt = f.getName() + " " + printOpt;
    }

    private void printOptSending() {
        try {
            osw = new OutputStreamWriter(socket.getOutputStream());
            bw = new BufferedWriter(osw);

            bw.write(printOpt);
            bw.newLine();
            bw.flush();
            //System.out.println("printOpt �쟾�넚 �셿猷�");

        } catch (IOException e) {
            e.printStackTrace();
        };
    }

    private void fileSending() {
        try {
            dos = new DataOutputStream(socket.getOutputStream());

            // �뙆�씪 �궡�슜�쓣 �씫�쑝硫댁꽌 �쟾�넚
            fis = new FileInputStream(f);
            bis = new BufferedInputStream(fis);

            int len;
            int size = 4096;
            byte[] data = new byte[size];
            while ((len = bis.read(data)) != -1) {
                control++;
                /*
                if (control % 10000 == 0) {
                    System.out.println("�쟾�넚以�..." + control / 10000);
                }
                */
                dos.write(data, 0, len);
            }
            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                osw.close();
                dos.close();
                bis.close();
                fis.close();
                //System.out.println("File �쟾�넚 �셿猷�");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        printOptSending();
        fileSending();
    }
}