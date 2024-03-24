package pass.model.labyrinth;

import pass.model.CustomLogger;
import pass.model.CustomRecordFormatter;
import pass.model.Idozitett;
import pass.model.human.Hallgato;
import pass.model.human.Oktato;
import pass.model.item.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
/* A Labirintus osztály felelős a játékterület létrehozásáért és kezeléséért. Ez az osztály
tartalmazza a labirintusban található szobákat és irányítja azok osztódását és
egyesülését. A Labirintus osztály feladata a szobák hálózatának kezelése. */
public class Labirintus implements Idozitett {
    private static String nev;

    private static int timeLeft;

    // HIANYOS!!!!
    static List<Szoba> szobak = new ArrayList<>();
    List<Hallgato> hallgatok;
    List<Oktato> oktatok;

    /**
     *
     * @param l - Teszthez neves konstruktor
     */
    public Labirintus(String l) {
        this.nev = l;
    }


    public String toString() {
        return nev + " :Labirintus";
    }

    /**
     *
     * @param sz - Labirintushoz hozzáadandó szoba.
     */
    public void addSzoba(Szoba sz){
        szobak.add(sz);
        CustomLogger.info("A labirintushoz hozzáadva a: " + sz);
    }

    /**
     *
     * @param t - Hátralevő idő beállítása
     */
    public void setTimeLeft(int t){this.timeLeft = t;}

    /**
     *
     * @param szoba - Kettéosztandó szoba
     */
    public void szobaFeloszt(Szoba szoba) {
        Szoba ujSzoba = new Szoba(szoba);
        CustomLogger.info(this + " új szobával rendelkezik: " + ujSzoba);
        szobak.add(ujSzoba);
    }

    /**
     *
     * @param sz1 - Ez a szoba marad
     * @param sz2 - Ezt vonjuk hozzá sz1-hez
     */
    public void szobakOsszevon(Szoba sz1, Szoba sz2){
        sz1.egyesit(sz2);
    }
//    public static void jatekVege(){CustomLogger.info("Játék vége, nyertek a Hallgatók");}
    public static void jatekNYert(){CustomLogger.info("Játék vége, nyertek a Hallgatók");}
    public static void jatekVeszt(){CustomLogger.info("Játék vége, vesztettek a Hallgatók");}
    public List<Szoba> getSzobak() {
        return szobak;
    }

    /**
     *
     * @param szobaSzam - A szobák száma
     * @param oktatoSzam - Az oktatók száma
     * @param targySzam - A tárgyak száma
     */
    public void init(int szobaSzam, int oktatoSzam, int targySzam) {
        if(szobaSzam < 1){
            CustomLogger.log(Level.WARNING, "Minimum egy Szoba szükséges");
            szobaSzam = 1;
            CustomLogger.log(Level.WARNING, "Default 1 értékkel folytatjuk.");
        }
        if(targySzam < 1){
            CustomLogger.log(Level.WARNING, "Minimum egy tárgy szükséges");
            targySzam = 1;
            CustomLogger.log(Level.WARNING, "Default 1 értékkel folytatjuk.");
        }
        if(oktatoSzam < 1){
            CustomLogger.log(Level.WARNING, "Minimum egy Oktató szükséges");
            oktatoSzam = 1;
            CustomLogger.log(Level.WARNING, "Default 1 értékkel folytatjuk.");
        }

        for (int i = 0; i < szobaSzam; i++) {
            Szoba szoba = new Szoba(5, "Szoba" + (i + 1));
            this.addSzoba(szoba);
        }
        if(szobaSzam > 1) {
            for (int i = 1; i < szobaSzam; i++) {
                Ajto ajto = new Ajto(this.getSzobak().get(i - 1), this.getSzobak().get(i), "Ajto" + (i));
                this.getSzobak().get(i - 1).addAjto(ajto);
                this.getSzobak().get(i).addAjto(ajto);
            }
        }
        if (oktatoSzam > szobaSzam * 5) {
            CustomLogger.log(Level.WARNING, "Csak " + (szobaSzam * 5) + " oktató hozható létre, mivel nincs elég szoba.");
            oktatoSzam = (szobaSzam * 5);
        }

        for (int i = 0; i < oktatoSzam; i++) {
            Oktato oktato = new Oktato("Oktato" + (i + 1));
            Szoba sz = this.getSzobak().get(i % szobaSzam);
            oktato.masikSzobabaLep(sz);
        }

        List<Targy> targyak = Arrays.asList(new Rongy("r"), new Pohar("p"), new TVSZ("t"), new Camembert("c"), new Maszk(5, "m"), new Tranzisztor("t"), new Logarlec("l"));
        for (int i = 0; i < targySzam; i++) {
            if(i == 0){
                Logarlec logarlec = new Logarlec("l");
                this.getSzobak().get(i % szobaSzam).addItem(logarlec);
            }else {
                Targy targy = targyak.get(i % targyak.size());
                this.getSzobak().get(i % szobaSzam).addItem(targy);
            }
        }
    }

    @Override
    public void tick() {
        timeLeft--;
        CustomLogger.info("A játékból még " + timeLeft + " kör van hátra.");
        if(timeLeft == 0) jatekVeszt();
    }

    /**
     *
     * @param sz - Kivenni kívánt szoba
     */
    public static void szobaKivesz(Szoba sz){
        szobak.remove(sz);
        CustomLogger.info("A labirintusból kikerült a " + sz);
    }
}
