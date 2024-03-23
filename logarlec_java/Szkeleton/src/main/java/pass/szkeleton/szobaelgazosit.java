package pass.szkeleton;

import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;
import java.util.HashMap;
import java.util.Map;

public class szobaelgazosit {

    private static Map<String, Object> map = new HashMap<>();

    public static void setUp() {
    Szoba sz = new Szoba(10);
    map.put("Hallgató", new Hallgato());
    map.put("Camambert", new Camembert());
    }


}
