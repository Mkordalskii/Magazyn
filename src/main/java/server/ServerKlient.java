package server;

import helpers.RodzajWiadomosciEnum;
import helpers.Wiadomosc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerKlient extends Thread {

    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private String nazwaKlienta;

    public ServerKlient(Socket socket) throws IOException {
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    @Override
    public void run() {
        for (ServerKlient klient : Server.klienci) {
            if (klient != this) {
                Wiadomosc wiadomoscNowyKlient = new Wiadomosc(RodzajWiadomosciEnum.nowyKlient, this.nazwaKlienta);
                this.wyslijWiadomosc(wiadomoscNowyKlient);
            }
        }
        while (true) {
            try {
                String wiadomoscKlienta = bufferedReader.readLine();
                System.out.println(nazwaKlienta + ":" + wiadomoscKlienta);
                Wiadomosc wiadomosc = Wiadomosc.deserializuj(wiadomoscKlienta);
                switch (wiadomosc.getRodzajWiadomosci()) {
                    // tutaj obslugujemy kazdy rodzaj wiadomosci od kilenta
                    case wyswietlWiadomosc:
                        wiadomosc.setZawartosc(this.nazwaKlienta + ": " + wiadomosc.getZawartosc());
                        for (ServerKlient klient : Server.klienci) {
                            klient.wyslijWiadomosc(wiadomosc);
                        }
                        break;
                    case wybierzNazwe:
                        nazwaKlienta = wiadomosc.getZawartosc();
                        for (ServerKlient klient : Server.klienci) {
                            Wiadomosc wiadomoscNowyKlient = new Wiadomosc(RodzajWiadomosciEnum.nowyKlient, this.nazwaKlienta);
                            klient.wyslijWiadomosc(wiadomoscNowyKlient);
                        }
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerKlient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void zamknijPolaczenie() throws IOException {
        printWriter.close();
        try {
            bufferedReader.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void wyslijWiadomosc(Wiadomosc wiadomosc) {
        printWriter.println(wiadomosc.serializuj());
    }
    public String getNazwaKlienta() {
        return nazwaKlienta;
    }
}
