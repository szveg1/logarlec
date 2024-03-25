package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;

public class HallgatoTeleportTest {

    public static void test() {
        Scanner scanner = new Scanner(System.in);
        CustomLogger.suppress();
        Ember e = new Hallgato("h");


        Szoba sz1 = new Szoba(1, "sz1");
        Szoba sz2 = new Szoba(1, "sz2");

        e.masikSzobabaLep(sz1);
        Targy t = new Tranzisztor("t1");
        Targy t2 = new Tranzisztor("t2");
        sz1.addItem(t);
        sz1.addItem(t2);
        CustomLogger.unsuppress();
        e.targyatFelvesz(t);
        e.targyatFelvesz(t2);

        e.targyatHasznal(t);
        e.targyatEldob(t2);

        e.masikSzobabaLep(sz2);
        e.targyatHasznal(t);

        System.out.println("Folytatashoz enter");
        scanner.nextLine();
    }
}
