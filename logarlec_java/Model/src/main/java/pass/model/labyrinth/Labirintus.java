package pass.model.labyrinth;

import pass.model.CustomLogger;
import pass.model.CustomRecordFormatter;
import pass.model.Idozitett;
import pass.model.human.Hallgato;
import pass.model.human.Oktato;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class Labirintus implements Idozitett {
    private String nev;

    // HIANYOS!!!!
    List<Szoba> szobak = new ArrayList<>();
    List<Hallgato> hallgatok;
    List<Oktato> oktatok;

    public Labirintus(String l) {
        this.nev = l;
    }

    @Override
    public String toString() {
        return nev + " :Labirintus";
    }

    public void addSzoba(Szoba sz){
        szobak.add(sz);
    }

    public void szobaFeloszt(Szoba szoba) {
        Szoba ujSzoba = new Szoba(szoba);
        CustomLogger.info(this + " új szobával rendelkezik: " + ujSzoba);
        szobak.add(ujSzoba);
    }

    public void szobakOsszevon(Szoba sz1, Szoba sz2){
        sz1.egyesit(sz2);
    }
    public static void jatekVege(){
        CustomLogger.info("Játék vége, nyeretek a Hallgatók");
    }
    public List<Szoba> getSzobak() {
        return szobak;
    }

    @Override
    public void tick() {

    }
}
