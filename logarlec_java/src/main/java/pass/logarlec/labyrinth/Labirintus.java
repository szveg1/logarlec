package pass.logarlec.labyrinth;

import pass.logarlec.Idozitett;
import pass.logarlec.human.Ember;
import pass.logarlec.human.Hallgato;
import pass.logarlec.human.Oktato;

import java.util.List;
import java.util.Map;

public class Labirintus implements Idozitett {
    // HIANYOS!!!!
    List<Szoba> szobak;
    List<Hallgato> hallgatok;
    List<Oktato> oktatok;

    public void szobaFeloszt(Szoba szoba) {
        Szoba ujSzoba = new Szoba(szoba);
        szobak.add(ujSzoba);
    }

    public void szobakOsszevon(){
        // TODO
    }

    @Override
    public void tick() {

    }
}
