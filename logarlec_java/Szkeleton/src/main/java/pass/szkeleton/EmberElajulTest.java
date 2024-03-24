package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;
import java.util.logging.Level;

public class EmberElajulTest {

    private static Map<String, Ember> emberMap = new HashMap<>();

    public static void test() {
        emberMap.put("oktato", new Oktato("o"));
        emberMap.put("hallgato", new Hallgato("h"));

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
        
        scanner.close();

        Szoba sz1 = new Szoba(1, "sz1");
        sz1.setPoisonous(4);
        Szoba sz2 = new Szoba(1, "sz2");

        sz1.emberBetesz(e);

        e.masikSzobabaLep(sz2);

        Targy r = new Rongy("r");
        sz1.addItem(r);
        e.targyatFelvesz(r);

    }
}
