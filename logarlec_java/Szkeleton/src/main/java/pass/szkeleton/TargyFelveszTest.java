package pass.szkeleton;

import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;
import java.util.logging.*;

import static pass.szkeleton.Main.logger;

public class TargyFelveszTest {
    private static Map<String, Ember> emberMap = new HashMap<>();
    private static Map<String, Targy> targyMap = new HashMap<>();

    private static Szoba sz;
    public static void setUp(){
        sz = new Szoba(1);
        emberMap.put("oktato", new Oktato("o"));
        emberMap.put("hallgato", new Hallgato("h"));
        targyMap.put("camembert", new Camembert());
        targyMap.put("logarlec", new Logarlec());
        targyMap.put("maszk", new Maszk(3));
        targyMap.put("pohar", new Pohar());
        targyMap.put("rongy", new Rongy());
        targyMap.put("tranzisztor", new Tranzisztor());
        targyMap.keySet().forEach(targy -> sz.addItem(targyMap.get(targy)));
    }

    public void test(){
        Ember e = null;
        Scanner scanner = new Scanner(System.in);

        logger.info("[Hallgató vagy oktató?] ");
        for (String ember : emberMap.keySet()) {
            System.out.print(" [" + ember + "]");
        }
        System.out.append("\n");

        do {
            String ember = scanner.nextLine();
            e = emberMap.get(ember);
            if (e == null) {
                logger.log(Level.WARNING, "Nem létező ember!");
            }
        } while (e == null);

        sz.emberBetesz(e);

        do {
            logger.info("Milyen tárgyat vegyen fel?");
            for (String targy : targyMap.keySet()) {
                System.out.print(" [" + targy + "]");
            }
            System.out.print(" [vége]");
            System.out.append("\n");
            String targy = scanner.nextLine();
            if (targy.equals("vége")) {
                break;
            }
            Targy t = targyMap.remove(targy);
            e.targyatFelvesz(t);
        } while(true);
    }
}
