package pass.szkeleton;

import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;

public class TargyFelveszTest {
    private static Map<String, Ember> emberMap = new HashMap<>();
    private static Map<String, Targy> targyMap = new HashMap<>();

    public static void setUp(){
        Szoba sz = new Szoba(1);
        emberMap.put("oktato", new Oktato());
        emberMap.put("hallgato", new Hallgato());
        targyMap.put("rongy", new Rongy());
    }

    public void test(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("[Hallgató vagy oktató?] ");
        for(String ember : emberMap.keySet()){
            System.out.print(" [" + ember + "]");
        }
        System.out.println();
        String ember = scanner.nextLine();
        Ember e = emberMap.get(ember);

        System.out.println("Hány tárgyat vegyen fel?");
        int n = scanner.nextInt();
        for(int i = 0; i < n; i++){
            System.out.println("Milyen tárgyat vegyen fel?");
            for(String targy : targyMap.keySet()){
                System.out.print(targy);
            }
            String targy = scanner.nextLine();
            Targy t = targyMap.get(targy);
            e.targyatFelvesz(t);
        }
    }
}
