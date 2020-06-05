package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class represent a server with only one thread.
 * Store text of client and give it back to client.
 * @author Sayed Mohammad Ali Mirkazemi
 * @version 1.0.0
 * @since 6/5/2020
 */
public class TextStorageServer{
    /**
     * This is constructor of this class and create server include welcoming socket and connection socket and Also do action.
     * @param port is port of serer
     */
    public TextStorageServer(int port){
        try(ServerSocket serverSocket=new ServerSocket(port)){
            System.out.println("Server started. Waiting for client....");
            try(Socket socket=serverSocket.accept()){
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
        } catch (IOException e) {
            System.out.println("There is problem in socket. (Connection socket)");
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        TextStorageServer textStorageServer=new TextStorageServer(5757);
    }
}

