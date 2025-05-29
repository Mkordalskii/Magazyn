package users;

import java.util.ArrayList;
import java.util.List;
import zamowienia.Zamowienie;


public abstract class User {
    private int id;
    private String nazwa;
    private String haslo;
    private Role rola;
    private List<Zamowienie> zamowienia;

    public User(int id, String nazwa, String haslo, Role rola) {
        this.id = id;
        this.nazwa = nazwa;
        this.haslo = haslo;
        this.rola = rola;
        zamowienia = new ArrayList<>();
    }
    public int getId() {return id;}
    public String getNazwa() {return nazwa;}
    public Role getRola() {return rola;}
    public boolean porownajHaslo(String haslo) {
        return this.haslo.equals(haslo);
    }
    public abstract boolean czyMozeDodawac();
    public abstract boolean czyMozeEdytowac();
    public abstract boolean czyMozeUsuwac();
}
