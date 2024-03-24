package pass.szkeleton;

import pass.model.human.Ember;
import pass.model.human.Hallgato;
import pass.model.human.Oktato;
import pass.model.item.Logarlec;
import pass.model.item.Targy;
import pass.model.labyrinth.Labirintus;
import pass.model.labyrinth.Szoba;

import java.util.HashMap;
import java.util.Map;

public class Logarlec_hallgato {
    private static Ember hallgato;
    private static Logarlec logarlec;
    private static Szoba szoba;

    private static Labirintus labirintus;

    public static void setUp() {
        hallgato = new Hallgato("h");
        logarlec = new Logarlec("log");
        szoba = new Szoba(5, "s");
        labirintus = new Labirintus("lab");
        szoba.addItem(logarlec);
        hallgato.masikSzobabaLep(szoba);
        labirintus.addSzoba(szoba);
    }


    public static void test() {
        setUp();
        hallgato.targyatFelvesz(logarlec);
        hallgato.tick();
    }
}
