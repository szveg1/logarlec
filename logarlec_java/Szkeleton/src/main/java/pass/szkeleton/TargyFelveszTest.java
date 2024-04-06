package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;
import java.util.logging.*;

public class TargyFelveszTest {
    private static Map<String, Ember> emberMap = new HashMap<>();
    private static Map<String, Targy> targyMap = new HashMap<>();

    private static Szoba sz;
    public static void setUp(){
        sz = new Szoba(1, "sz");
        emberMap.put("oktato", new Oktato("o"));
        emberMap.put("hallgato", new Hallgato("h"));
        targyMap.put("camembert", new Camembert("c"));
        targyMap.put("logarlec", new Logarlec("l"));
        targyMap.put("maszk", new Maszk(3, "m"));
        targyMap.put("pohar", new Pohar("p"));
        targyMap.put("rongy", new Rongy("r"));
        targyMap.put("tranzisztor", new Tranzisztor("t"));
        targyMap.put("tvsz", new TVSZ("t"));
        targyMap.put("hamislec", new HamisLec("hl"));
        targyMap.put("hamistvsz", new HamisTVSZ("ht"));
        targyMap.put("hamismaszk", new HamisMaszk("hm"));
    }

    public static void test(){
        System.out.println("TargyFelveszTest");
        setUp();

        Ember e;
        Scanner scanner = new Scanner(System.in);

        System.out.println("[Hallgató vagy oktató vesz fel tárgy(ak)at?] ");
        for (String ember : emberMap.keySet()) {
            System.out.print(" [" + ember + "]");
        }
        System.out.append("\n");

        do {
            String ember = scanner.nextLine();
            e = emberMap.get(ember);
            if (e == null) {
                CustomLogger.log(Level.WARNING, "Nem létező ember!");
            }
        } while (e == null);

        CustomLogger.suppress();
        e.masikSzobabaLep(sz);
        CustomLogger.unsuppress();

        do {
            e.tick();
            System.out.println("Milyen tárgyat vegyen fel?");
            for (String targy : targyMap.keySet()) {
                System.out.print(" [" + targy + "]");
            }
            System.out.print(" [vege]");
            System.out.append("\n");
            String targyNev = scanner.nextLine();
            if (targyNev.equals("vege")) {
                break;
            }
            else if(!targyMap.containsKey(targyNev)){
                CustomLogger.log(Level.WARNING, "Nem létező tárgy!");
                continue;
            }
            Targy t = targyMap.remove(targyNev);
            CustomLogger.suppress();
            sz.addItem(t);
            CustomLogger.unsuppress();
            e.targyatFelvesz(t);
            if(sz.getItems().contains(t)){
                targyMap.put(targyNev, t);
            }
        } while(true);

        System.out.println("Folytatashoz enter");
        scanner.nextLine();
    }
}
