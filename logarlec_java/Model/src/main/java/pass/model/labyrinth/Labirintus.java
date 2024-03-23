package pass.model.labyrinth;

import pass.model.Idozitett;
import pass.model.human.Hallgato;
import pass.model.human.Oktato;

import java.util.List;

public class Labirintus implements Idozitett {
    // HIANYOS!!!!
    List<Szoba> szobak;
    List<Hallgato> hallgatok;
    List<Oktato> oktatok;
    public void addSzoba(Szoba sz){
        szobak.add(sz);
    }

    public void szobaFeloszt(Szoba szoba) {
        Szoba ujSzoba = new Szoba(szoba);
        szobak.add(ujSzoba);
    }

    public void szobakOsszevon(){

    }

    @Override
    public void tick() {

    }
}
