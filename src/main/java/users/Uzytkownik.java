package users;

import java.util.ArrayList;
import java.util.List;
import zamowienia.Zamowienie;

public class Uzytkownik extends User {

    public Uzytkownik(int id, String nazwa, String haslo, Role rola) {
        super(id, nazwa, haslo, rola);
    }

    @Override
    public boolean czyMozeDodawac() {
        return false;
    }

    @Override
    public boolean czyMozeEdytowac() {
        return false;
    }

    @Override
    public boolean czyMozeUsuwac() {
        return false;
    }
}
