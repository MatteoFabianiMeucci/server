package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread{
    Socket s;

    public MioThread(Socket s){
        this.s = s;
    }

    public void run(){
        try (BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
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
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        
    }
}
