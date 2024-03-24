package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;
import java.util.logging.Level;

public class TargyEldobTest {

    private static Map<String, Ember> emberMap = new HashMap<>();
    private static Map<String, Targy> targyMap = new HashMap<>();

    public static void test(){
        Ember e = null;
        Szoba sz = new Szoba(1, "sz");
        emberMap.put("oktato", new Oktato("o"));
        emberMap.put("hallgato", new Hallgato("h"));
/*        targyMap.put("rongy", new Rongy("r"));
        targyMap.put("camembert", new Camembert("c"));
        targyMap.put("maszk", new Maszk(1, "m"));
        targyMap.put("pohar", new Pohar("p"));
        targyMap.put("tranzisztor", new Tranzisztor("tr"));
        targyMap.put("tvsz", new TVSZ("tv"));*/

        Scanner scanner = new Scanner(System.in);
        CustomLogger.log(Level.INFO, "[Hallgató vagy oktató dob el tárgyat?] ");
        for(String ember : emberMap.keySet()){
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

        e.masikSzobabaLep(sz);

        CustomLogger.log(Level.INFO, "Hány tárgy legyen az inventoryban? (oktato max 1, hallgato max 5)");
        int targySzam = scanner.nextInt();
        scanner.nextLine();
        for(int i = 1; i <= targySzam; i++){
            Targy t = new Rongy("r"+i);
            sz.addItem(t);
            e.targyatFelvesz(t);
            if(!sz.getItems().contains(t))
            targyMap.put("r"+i, t);
        }
        do {
            CustomLogger.log(Level.INFO, "Milyen tárgyat dobjon el?");
            for (String targy : targyMap.keySet()) {
                System.out.print("[" + targy + "] ");
            }
            System.out.append("\n");
            String targyNev = scanner.nextLine();
            Targy t = targyMap.get(targyNev);
            e.targyatEldob(t);
            if(sz.getItems().contains(t)){
                targyMap.remove(targyNev, t);
            }
        }while(!e.getItems().isEmpty());
    }
}
