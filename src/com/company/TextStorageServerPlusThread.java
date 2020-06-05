package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class represent a server with multi thread.
 * Store text of client and give it back to client.
 * @author Sayed Mohammad Ali Mirkazemi
 * @version 1.0.0
 * @since 6/5/2020
 */
public class TextStorageServerPlusThread{
    /**
     * This is constructor of this class and create server include welcoming socket and connection socket and Also do action.
     * @param port is port of serer
     */
    public TextStorageServerPlusThread(int port){
        ExecutorService pool= Executors.newCachedThreadPool();
        try(ServerSocket serverSocket=new ServerSocket(port)){
            System.out.println("Server started. Waiting for client....");
            while (true){
                Socket socket=serverSocket.accept();
                pool.execute(new Handler(socket));
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


