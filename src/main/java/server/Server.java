package server;

import config.Konfiguracja;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import towar.Towar;

public class Server {

    private ServerSocket serverSocket;
    public static Set<ServerKlient> klienci;
    public static List<Towar> towary;

    public Server() throws IOException, InterruptedException {
        serverSocket = new ServerSocket(Konfiguracja.PORT);
        klienci = new HashSet<ServerKlient>();
        towary = new ArrayList<Towar>();
        uruchom();
    }

    public void uruchom() throws InterruptedException {
        while (true) {
            try {
                System.out.println("Serwer oczekuje na klienta");
                Socket socketKlienta = serverSocket.accept();
                System.out.println("Polaczono z klientem.");
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socketKlienta.getInputStream()));
//                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socketKlienta.getOutputStream()), true);
//                printWriter.println("Witaj na serwerze!");
                ServerKlient serverKlient = new ServerKlient(socketKlienta);
                klienci.add(serverKlient);
                serverKlient.start();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
