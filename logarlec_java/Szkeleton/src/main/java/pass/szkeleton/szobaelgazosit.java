package pass.szkeleton;

import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;
import java.util.HashMap;
import java.util.Map;

public class szobaelgazosit {

    private static Map<String, Targy> map = new HashMap<>();
    private static Map<String, Ember> map2 = new HashMap<>();

    public static void test() {
    Szoba sz = new Szoba(10, "sz");
    Ember h = new Hallgato("h");
    Targy c = new Camembert("c");

    map2.put("Hallgató", h);
    map.put("Camambert", c);

    sz.addItem(c);
    sz.emberBetesz(h);

    h.targyatFelvesz(c);
    h.targyatHasznal(c);

    }


}
