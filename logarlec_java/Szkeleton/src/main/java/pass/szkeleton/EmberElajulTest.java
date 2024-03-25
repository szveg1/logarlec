package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;
import java.util.logging.Level;

public class EmberElajulTest {

    private static Map<String, Ember> emberMap = new HashMap<>();
    private static Map<String, Boolean> choiceMap = new HashMap<>();

    public static void test() {
        emberMap.put("oktato", new Oktato("o"));
        emberMap.put("hallgato", new Hallgato("h"));
        choiceMap.put("igen", true);
        choiceMap.put("nem", false);

        Scanner scanner = new Scanner(System.in);
        System.out.print("[Hallgató vagy oktató?] ");
        for(String ember : emberMap.keySet()){
            System.out.print(" [" + ember + "]");
        }
        System.out.println();

        Ember e = null;
        do {
            String ember = scanner.nextLine();
            e = emberMap.get(ember);
            if (e == null) {
                CustomLogger.log(Level.WARNING, "Nem létező ember!");
            }
        } while (e == null);

        System.out.println("[Legyen rajta maszk?]" + "\n" + "[igen] [nem]");
        Boolean choice = null;
        do {
            String choiceS = scanner.nextLine();
            choice = choiceMap.get(choiceS);
            if (choice == null) {
                CustomLogger.log(Level.WARNING, "Nem jól írtad le!");
            }
        } while (choice == null);

        if (choice) {
            Targy maszk = new Maszk(3, "M");
            e.targyatFelvesz(maszk);
            e.tick();
        }

        Szoba sz1 = new Szoba(1, "sz1");
        sz1.setPoisonous(4);
        Szoba sz2 = new Szoba(1, "sz2");

        sz1.emberBetesz(e);



        Targy r = new Rongy("r");
        sz1.addItem(r);
        e.targyatFelvesz(r);
        e.masikSzobabaLep(sz2);

        System.out.println("Folytatashoz enter");
        scanner.nextLine();
    }
}
