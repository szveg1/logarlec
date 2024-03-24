package pass.model.labyrinth;

import pass.model.CustomRecordFormatter;
import pass.model.Idozitett;
import pass.model.human.Hallgato;
import pass.model.human.Oktato;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class Labirintus implements Idozitett {
    private static final Logger labirintusLogger = Logger.getLogger(Labirintus.class.getSimpleName());
    static{
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new CustomRecordFormatter());
        labirintusLogger.setUseParentHandlers(false);
        labirintusLogger.addHandler(handler);
    }
    // HIANYOS!!!!
    List<Szoba> szobak = new ArrayList<>();
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

    public List<Szoba> getSzobak() {
        return szobak;
    }

    @Override
    public void tick() {

    }
}
