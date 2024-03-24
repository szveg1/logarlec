package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;

public class TeliInventorybaFelvesz
{
    private static Szoba sz1 = new Szoba(5, "sz1");

    private static Ember e1;
    private static Hallgato h1 = new Hallgato("h1");
    private static Oktato o1 = new Oktato("o1");

    private static Maszk m1 = new Maszk(10, "m1");
    private static Rongy r1 = new Rongy("r1");
    private static Pohar p1 = new Pohar("p1");
    private static TVSZ tv1 = new TVSZ("tv1");
    private static Tranzisztor tr1 = new Tranzisztor("tr1");
    private static Camembert c1 = new Camembert("c1");

    private static Map<String, Szoba> szobampa = new HashMap<>();
    private static Map<String, Ember>  embermpa = new HashMap<>();
    private static Map<String, Targy>  targympa = new HashMap<>();

    public static void setUp() {
        embermpa.put("oktato", o1);
        embermpa.put("hallgato", h1);
        szobampa.put("kiinduloszoba", sz1);
        targympa.put("maszk", m1);
        targympa.put("rongy", r1);
        targympa.put("pohar", p1);
        targympa.put("tvsz", tv1);
        targympa.put("tranzisztor1", tr1);
        targympa.put("camambert", c1);
    }

    public void test()
    {
        Scanner scanner = new Scanner(System.in);
        CustomLogger.info("[Hallgató vagy oktató?]\n");
        String ember = scanner.nextLine();
        Ember e = embermpa.get(ember);
        sz1.emberBetesz(e);
        sz1.addItem(m1);
        sz1.addItem(r1);
        sz1.addItem(p1);
        sz1.addItem(tv1);
        sz1.addItem(tr1);
        sz1.addItem(c1);
        if(e == o1){
            e.targyatFelvesz(m1);
            sz1.removeItem(m1);

            e.targyatFelvesz(r1);
            sz1.removeItem(r1);
            if(e.getItems().size() == 1){CustomLogger.info("valami stimmel");}
            else if(e.getItems().size() > 1){CustomLogger.info("valami nem stimmel");}
        }
        else if(e == h1){
            e.targyatFelvesz(m1);
            sz1.removeItem(m1);

            e.targyatFelvesz(r1);
            sz1.removeItem(r1);

            e.targyatFelvesz(p1);
            sz1.removeItem(p1);

            e.targyatFelvesz(c1);
            sz1.removeItem(c1);

            e.targyatFelvesz(tv1);
            sz1.removeItem(tv1);

            e.targyatFelvesz(tr1);
            sz1.removeItem(tr1);

            if(e.getItems().size() == 5){CustomLogger.info("valami stimmel");}
            else if(e.getItems().size() > 5){CustomLogger.info("valami nem stimmel");}

        }

    }
}
