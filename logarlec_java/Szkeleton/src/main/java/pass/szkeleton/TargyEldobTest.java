package pass.szkeleton;

import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;

public class TargyEldobTest {
<<<<<<< HEAD
    public static void setUp(){
        Szoba sz = new Szoba(1);
        Ember h = new Hallgato();
        Ember o = new Oktato();
        Targy r = new Rongy();
        sz.emberBetesz(h);
        sz.emberBetesz(o);
        sz.addItem(r);
        h.targyatFelvesz(r);
    }

    public void test(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hallgató vagy oktató?");
        String ember = scanner.nextLine();

=======

    private static Map<String, Ember> emberMap = new HashMap<>();
    private static Map<String, Targy> targyMap = new HashMap<>();

    public static void test(){
        Szoba sz = new Szoba(1);
        emberMap.put("oktato", new Oktato());
        emberMap.put("hallgato", new Hallgato());
        targyMap.put("rongy", new Rongy());
        targyMap.put("camembert", new Camembert());
        targyMap.put("maszk", new Maszk());
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
        sz.addEmber(e);

        System.out.println("Milyen tárgyat dobjon fel?");
        System.out.print(targy);
        String targy = scanner.nextLine();
        Targy t = targyMap.get(targy);
        e.addItem(t);
        e.targyatEldob(t);

        if (sz.getItems().size() == 1) System.out.println("Tárgy bekerült a szobába");
        if (sz.getItems().size() != 1) System.out.println("Tárgy nem került a szobába");

        if (e.getItems().size() == 0) System.out.println("Tárgyat eldobta az ember");
        if (e.getItems().size() != 0) System.out.println("Tárgyat nem dobta el az ember");
        
>>>>>>> 4f746c606a1555357c27c42119c3cda11b4e4f58
    }
}
