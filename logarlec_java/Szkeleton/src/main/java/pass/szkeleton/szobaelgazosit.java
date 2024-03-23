package pass.szkeleton;

import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;
import java.util.HashMap;
import java.util.Map;

public class szobaelgazosit {

<<<<<<< HEAD
    private static Map<String, Targy> map = new HashMap<>();
    private static Map<String, Ember> map2 = new HashMap<>();

    public static void test() {
    Szoba sz = new Szoba(10);
    Ember h = new Hallgato();
    Targy c = new Camembert();

    map2.put("Hallgató", h);
    map.put("Camambert", c);

    sz.addItem(c);
    sz.emberBetesz(h);

    h.targyatFelvesz(c);
    c.hasznal(null);

    System.out.println("Szoba mérgezett ennyi ideig: " + sz.isPoisonous());

=======
    private static Map<String, Object> map = new HashMap<>();

    public static void setUp() {
    Szoba sz = new Szoba(10);
    map.put("Hallgató", new Hallgato());
    map.put("Camambert", new Camembert());
>>>>>>> 4f746c606a1555357c27c42119c3cda11b4e4f58
    }


}
