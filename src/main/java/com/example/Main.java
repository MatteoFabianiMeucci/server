package com.example;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server avviato!");
        ServerSocket s1 = new ServerSocket(3000);

        do{
            Socket s = s1.accept();
            System.out.println("un Client si è collegato");

            MioThread t = new MioThread(s);
            t.start();
        } while(true);
    }
}