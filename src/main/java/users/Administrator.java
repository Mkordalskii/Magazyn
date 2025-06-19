package users;

import java.util.ArrayList;
import java.util.List;
import zamowienia.Zamowienie;

public class Administrator extends User {
    public Administrator(int id, String nazwa, String haslo, Role rola) {
       super(id, nazwa, haslo, rola);
    }
    @Override
    public boolean czyMozeDodawac(){return true;}
    @Override
    public boolean czyMozeEdytowac(){return true;}
    @Override
    public boolean czyMozeUsuwac(){return true;}
}

