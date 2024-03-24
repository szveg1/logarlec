package pass.szkeleton;

import pass.model.human.Ember;
import pass.model.labyrinth.Ajto;
import pass.model.labyrinth.Szoba;

import java.util.HashMap;
import java.util.Map;

public class Ajtojeleniktunik {

    private static Szoba sz1 = new Szoba(5, "sz1");
    private static Szoba sz2 = new Szoba(2, "sz2");
    private static Ajto a1 = new Ajto(sz1, sz2);


    private static Map<String, Szoba> szobampa = new HashMap<>();
    private static Map<String, Ember>  embermpa = new HashMap<>();
    private static Map<String, Ajto>  ajtpmpa = new HashMap<>();

    public static void setUp() {
        szobampa.put("kiinduloszoba", sz1);
        szobampa.put("celszoba", sz2);
        ajtpmpa.put("ajto", a1);
    }
}
