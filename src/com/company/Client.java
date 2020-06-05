package com.company;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * This class represent a client that sent text ot server.
 * @author Sayed Mohammad Ali Mirkazemi
 * @version 1.0.0
 * @since 6/5/2020
 */
public class Client {
    /**
     * This is constructor of this class and create server include welcoming socket and connection socket.
     * @param address is address of serer
     * @param port is port of serer
     */
    public Client(String address, int port){
        try(Socket socket=new Socket(address,port)){
            System.out.println("Connected to the server.");
            OutputStream outputStream=socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] buffer=new byte[2048];
            Scanner scanner=new Scanner(System.in);
            System.out.println("Please enter your text: (word \"over\" finish process)");
            String line="";
            while (!line.equals("over")){
                line=scanner.nextLine();
                outputStream.write(line.getBytes());
                int n=inputStream.read(buffer);
                System.out.println(new String(buffer,0,n));
            }
            System.out.println("Disconnected from the server.");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        Client client=new Client("127.0.0.1",5757);
    }
}
