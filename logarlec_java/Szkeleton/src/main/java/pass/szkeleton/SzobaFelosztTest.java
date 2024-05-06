package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.Ember;
import pass.model.human.Hallgato;
import pass.model.item.Rongy;
import pass.model.labyrinth.Labirintus;
import pass.model.labyrinth.Szoba;

import java.util.Scanner;
import java.util.logging.Level;

public class SzobaFelosztTest {
    public static void test() {
        Labirintus labirintus = Labirintus.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Mekkora legyen a szoba ferohelye?");
        String s = scanner.nextLine();
        int m = Integer.parseInt(s);

        CustomLogger.suppress();
        Szoba sz1 = new Szoba(m, "sz1");
        labirintus.addSzoba(sz1);
        CustomLogger.unsuppress();

        System.out.println("Hany ember legyen a szobaban?");
        s = scanner.nextLine();
        m = Integer.parseInt(s);
        if (m > sz1.getFerohely()) {
            CustomLogger.log(Level.WARNING, "Ennyi ember nem fer el a szobaban! " + sz1.getFerohely() + " ember fog a szobaba kerülni.");
            m = sz1.getFerohely();
        }
        for (int i = 0; i < m; i++) {
            CustomLogger.suppress();
            Ember e = new Hallgato("h" + i);
            e.masikSzobabaLep(sz1);
            CustomLogger.unsuppress();
        }

        System.out.println("Hany targy legyen a szobaban?");
        s = scanner.nextLine();
        m = Integer.parseInt(s);
        for (int i = 0; i < m; i++) {
            CustomLogger.suppress();
            sz1.addItem(new Rongy("r" + i));
            CustomLogger.unsuppress();
        }

        labirintus.szobaFeloszt(sz1);

        System.out.println("Folytatashoz enter");
        scanner.nextLine();
    }
}
