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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hallgató vagy oktató dob el tárgyat? ");
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

        CustomLogger.suppress();
        e.masikSzobabaLep(sz);
        CustomLogger.unsuppress();

        System.out.println( "Hány tárgy legyen az inventoryban? (oktato max 1, hallgato max 5)");
        int targySzam = scanner.nextInt();
        scanner.nextLine();
        for(int i = 1; i <= targySzam; i++){
            Targy t = new Rongy("r"+i);
            CustomLogger.suppress();
            sz.addItem(t);
            CustomLogger.unsuppress();
            e.targyatFelvesz(t);
            if(!sz.getItems().contains(t))
            targyMap.put("r"+i, t);
        }
        do {
            System.out.println( "Melyik tárgyat dobja el?");
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
