package pass.szkeleton;

import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;

public class TargyEldobTest {

    private static Map<String, Ember> emberMap = new HashMap<>();
    private static Map<String, Targy> targyMap = new HashMap<>();

    public static void test(){
        Szoba sz = new Szoba(1);
        emberMap.put("oktato", new Oktato("o"));
        emberMap.put("hallgato", new Hallgato("h"));
        targyMap.put("rongy", new Rongy());
        targyMap.put("camembert", new Camembert());
        targyMap.put("maszk", new Maszk(1));
        targyMap.put("pohar", new Pohar());
        targyMap.put("tranzisztor", new Tranzisztor());
        targyMap.put("tvsz", new TVSZ());

        Scanner scanner = new Scanner(System.in);
        System.out.print("[Hallgató vagy oktató?] ");
        for(String ember : emberMap.keySet()){
            System.out.print(" [" + ember + "]");
        }
        System.out.println();
        String ember = scanner.nextLine();
        Ember e = emberMap.get(ember);
        sz.emberBetesz(e);

        System.out.println("Milyen tárgyat dobjon fel?");
        String targy = scanner.nextLine();
        Targy t = targyMap.get(targy);
        e.addItem(t);
        e.targyatEldob(t);

        if (e.getItems().size() == 0) System.out.println("Tárgyat eldobta az ember");
        if (e.getItems().size() != 0) System.out.println("Tárgyat nem dobta el az ember");

        if (sz.getItems().size() == 1) System.out.println("Tárgy bekerült a szobába");
        if (sz.getItems().size() != 1) System.out.println("Tárgy nem került a szobába");

    }
}
