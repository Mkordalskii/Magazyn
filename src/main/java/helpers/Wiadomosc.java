package helpers;

public class Wiadomosc {

    private RodzajWiadomosciEnum rodzajWiadomosci;
    private String zawartosc;

    public RodzajWiadomosciEnum getRodzajWiadomosci() {
        return rodzajWiadomosci;
    }

    public void setRodzajWiadomosci(RodzajWiadomosciEnum rodzajWiadomosci) {
        this.rodzajWiadomosci = rodzajWiadomosci;
    }

    public String getZawartosc() {
        return zawartosc;
    }

    public void setZawartosc(String zawartosc) {
        this.zawartosc = zawartosc;
    }

    public Wiadomosc(RodzajWiadomosciEnum rodzajWiadomosci, String zawartosc) {
        this.rodzajWiadomosci = rodzajWiadomosci;
        this.zawartosc = zawartosc;
    }
    
    public String serializuj() {
        return getRodzajWiadomosci().name() + ";" + getZawartosc();
    }
    
    public static Wiadomosc deserializuj(String wiadomosc){
        String[] wiadomoscLokalna = wiadomosc.split(";");
        Wiadomosc nowaWiadomosc = new Wiadomosc(RodzajWiadomosciEnum.valueOf(wiadomoscLokalna[0]), wiadomoscLokalna[1]);
        return nowaWiadomosc;
    }
}
