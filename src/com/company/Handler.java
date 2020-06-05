package com.company;

import java.io.*;
import java.net.Socket;

/**
 * This class implement "Runnable" interface for handler multi thread.
 * Store text of client and give it back to client.
 * @author Sayed Mohammad Ali Mirkazemi
 * @version 1.0.0
 * @since 6/5/2020
 */
public class Handler implements Runnable{
    Socket socket;

    /**
     * This is constructor of this class and fill socket field.
     * @param socket is welcoming socket of this class
     */
    public Handler(Socket socket){
        this.socket=socket;
    }

    /**
     * This is a override of run method and store text and give it back to client.
     */
    @Override
    public void run() {
        try{
            System.out.println("Client accepted.");
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            byte[] buffer=new byte[2048];
            StringBuilder stringBuilder=new StringBuilder();
            String line="";
            while (!line.equals("over")){
                int n=inputStream.read(buffer);
                line=new String(buffer,0,n);
                stringBuilder.append(line);
                line=stringBuilder.toString();
                outputStream.write(line.getBytes());
            }
            System.out.println("Client Disconnected.");
        } catch (IOException e) {
            System.out.println("There is problem in server socket. (Welcoming socket)");
            e.printStackTrace();
        }
    }
}
