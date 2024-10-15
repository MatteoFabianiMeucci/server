package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.print.DocFlavor.STRING;

public class MioThread extends Thread{
    Socket s;

    public MioThread(Socket s){
        this.s = s;
    }

    public void run(){
        try (BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            String stringRicevuta;
            String stringInviata;
            boolean acceso = true;

            do{
                stringRicevuta = in.readLine();
                
                switch (stringRicevuta) {
                    case "M":
                        out.writeBytes("?" + "\n");
                        stringInviata = in.readLine().toUpperCase();
                        out.writeBytes(stringInviata + "\n");
                        break;

                    case "m":
                        out.writeBytes("?" + "\n");
                        stringInviata = in.readLine().toLowerCase();
                        out.writeBytes(stringInviata + "\n");
                        break;

                    case "r":
                        out.writeBytes("?" + "\n");
                        stringInviata = in.readLine();

                        StringBuilder reverse = new StringBuilder();
                        reverse.append(stringInviata);
                        reverse.reverse();
                        stringInviata = reverse.toString();

                        out.writeBytes(stringInviata + "\n");
                        break;

                    case "c":
                        out.writeBytes("?" + "\n");
                        int conto = in.readLine().length();
                        stringInviata = String.valueOf(conto);
                        out.writeBytes(stringInviata + "\n");

                    case "!":
                        acceso = false;
                        break;


                    default:
                        out.writeBytes("#" + "\n");
                        break;
                }
            } while(acceso);
            s.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        
    }
}
