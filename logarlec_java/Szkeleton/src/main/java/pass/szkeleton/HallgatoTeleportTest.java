package pass.szkeleton;

import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;

public class HallgatoTeleportTest {

    public static void test() {

        Ember e = new Hallgato("h");


        Szoba sz1 = new Szoba(1, "sz1");
        Szoba sz2 = new Szoba(1, "sz1");

        e.masikSzobabaLep(sz1);
        Targy t = new Tranzisztor("t");
        Targy t2 = new Tranzisztor("t2");
        sz1.addItem(t);
        sz1.addItem(t2);
        e.targyatFelvesz(t);
        e.targyatFelvesz(t2);

        e.targyatHasznal(t);
        e.targyatEldob(t2);

        e.masikSzobabaLep(sz2);
        e.targyatHasznal(t);

    }
}
