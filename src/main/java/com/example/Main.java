package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server avviato!");

        ServerSocket s1 = new ServerSocket(3000);
        Socket s = s1.accept();

        System.out.println("un Client si è collegato");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        String stringRicevuta;
        do{
            stringRicevuta = in.readLine();
            
            if(!stringRicevuta.equals("!")){
                System.out.println("La stringa ricevuta: " + stringRicevuta);
                String stringMaiuscola = stringRicevuta.toUpperCase();
                out.writeBytes(stringMaiuscola + "\n");
            }
        } while(!stringRicevuta.equals("!"));
        

        s.close();
        s1.close();
    }
}