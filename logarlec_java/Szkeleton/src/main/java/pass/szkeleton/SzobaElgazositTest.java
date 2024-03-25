package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SzobaElgazositTest {

    private static Map<String, Targy> map = new HashMap<>();
    private static Map<String, Ember> map2 = new HashMap<>();

    public static void test() {
        Scanner scanner = new Scanner(System.in);
        Szoba sz = new Szoba(10, "sz");
        Ember h = new Hallgato("h");
        Targy c = new Camembert("c");

        map2.put("Hallgató", h);
        map.put("Camambert", c);

        CustomLogger.suppress();
        sz.addItem(c);
        sz.emberBetesz(h);
        CustomLogger.unsuppress();

        h.targyatFelvesz(c);
        h.targyatHasznal(c);

        System.out.println("Folytatashoz enter");
        scanner.nextLine();
    }


}
