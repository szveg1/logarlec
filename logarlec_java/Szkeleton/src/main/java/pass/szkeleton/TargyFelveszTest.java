package pass.szkeleton;

import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;

public class TargyFelveszTest {
    private static Map<String, Ember> emberMap = new HashMap<>();
    private static Map<String, Targy> targyMap = new HashMap<>();

    private static Szoba sz;
    public static void setUp(){
        sz = new Szoba(1);
        emberMap.put("oktato", new Oktato());
        emberMap.put("hallgato", new Hallgato());
        targyMap.put("camembert", new Camembert());
        targyMap.put("logarlec", new Logarlec());
        targyMap.put("maszk", new Maszk(3));
        targyMap.put("pohar", new Pohar());
        targyMap.put("rongy", new Rongy());
        targyMap.put("tranzisztor", new Tranzisztor());
    }

    public void test(){
        Ember e = null;
        String indent = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("TargyFelveszTest: ");

        indent += "\t";

        System.out.print(indent + "Ki veszi fel a tárgyat?");
        for(String ember : emberMap.keySet()){
            System.out.print(" [" + ember + "]");
        }

        System.out.println();

        do{
            e = emberMap.get(scanner.nextLine());
            if(e == null){
                System.out.println(indent + "Nem létezik ilyen ember. Próbáld újra.");
            } else {
                e.setJelenlegiSzoba(sz);
                System.out.println(indent + "Az ember a" + sz.toString() + " szobában van.");
            }
        }while(e == null);

        System.out.print(indent + "Milyen tárgyat vesz fel?");
        for(String targy : targyMap.keySet()){
            System.out.print(" [" + targy + "]");
        }
        System.out.print(" [vége]");

        String targy = scanner.nextLine();
        do {
            if (targyMap.get(targy) == null) {
                if(!targy.equals("vége"))
                    System.out.println(indent + "Nem létezik ilyen tárgy. Próbáld újra.");
            } else {
                e.targyatFelvesz(targyMap.get(targy));
            }

        } while(!targy.equals("vége"));

        System.out.println("\nTargyFelveszTest: tesztelés vége.");
    }
}
