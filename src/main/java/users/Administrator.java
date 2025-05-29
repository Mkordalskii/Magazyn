package users;

import java.util.ArrayList;
import java.util.List;

public class Administrator extends User {
    private int id;
    private String nazwa;
    private String haslo;
    private Role rola;
    private List<Zamowienie> zamowienia;

    public Administrator(int id, String nazwa, String haslo, Role rola) {
       super(id, nazwa, haslo, rola);
       zamowienia = new ArrayList<>();
    }
    public int getId() {return id;}
    public String getNazwa() {return nazwa;}
    public Role getRola() {return rola;}
    public boolean porownajHaslo(String haslo) {
        return this.haslo.equals(haslo);
    }
    @Override
    public boolean czyMozeDodawac(){return true;}
    @Override
    public boolean czyMozeEdytowac(){return true;}
    @Override
    public boolean czyMozeUsuwac(){return true;}
}

