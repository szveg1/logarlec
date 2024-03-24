package pass.szkeleton;

import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;

public class HallgatoTeleportTest {

    public static void test() {

        Ember e = new Hallgato("h");


        Szoba sz1 = new Szoba(1);
        Szoba sz2 = new Szoba(1);

        sz1.emberBetesz(e);
        Targy t = new Tranzisztor();
        Targy t2 = new Tranzisztor();
        sz1.addItem(t);
        e.targyatFelvesz(t);
        if (e.getItems().get(0) == t) System.out.println("hallgato felvett egy tranzisztort");
        else System.out.println("valami nem stimmel");

        e.targyatFelvesz(t2);
        if (e.getItems().get(1) == t2) System.out.println("hallgato felvett egy másik tranzisztort");
        else System.out.println("valami nem stimmel");


        e.masikSzobabaLep(sz2);
        if (sz1.getEmberek().size() == 0 && sz2.getEmberek().size() == 1) System.out.println("hallgato atment egy masik szobaba");
        else System.out.println("valami nem stimmel");

    }
}
