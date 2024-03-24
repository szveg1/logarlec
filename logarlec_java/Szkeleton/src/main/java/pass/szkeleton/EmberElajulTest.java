package pass.szkeleton;

import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;

public class EmberElajulTest {

    private static Map<String, Ember> emberMap = new HashMap<>();

    public static void test() {
        emberMap.put("oktato", new Oktato());
        emberMap.put("hallgato", new Hallgato());

        Scanner scanner = new Scanner(System.in);
        System.out.print("[Hallgató vagy oktató?] ");
        for(String ember : emberMap.keySet()){
            System.out.print(" [" + ember + "]");
        }
        System.out.println();
        String ember = scanner.nextLine();
        Ember e = emberMap.get(ember);

        Szoba sz1 = new Szoba(1);
        Szoba sz2 = new Szoba(1);

        sz1.addEmber(e);

        e.ajulas();
        if (e.getAjult()) System.out.println("ember elajult");
        else System.out.println("valami nem stimmel");

        e.masikSzobabaLep(sz2);
        if (sz1.getEmberek().size() == 1 && sz2.getEmberek().size() == 0) System.out.println("ember nem tud mozogni");
        else System.out.println("valami nem stimmel");

        sz1.addItem(new Rongy());
        if (e.getItems().size() == 0) System.out.println("ember nem tud tárgyat felvenni");
        else System.out.println("valami nem stimmel");

    }
}
