package pass.szkeleton;
import pass.model.CustomLogger;
import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;
import java.util.logging.Level;

public class InitTest {

    public static void initTest() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Kérem adja meg, hogy hány szoba legyen:");
        int szobaSzam = scanner.nextInt();

        System.out.println("Kérem adja meg, hogy hány oktató legyen:");
        int oktatoSzam = scanner.nextInt();

        System.out.println("Kérem adja meg, hogy hány tárgy legyen:");
        int targySzam = scanner.nextInt();

        Labirintus labirintus = Labirintus.getInstance();
        labirintus.init(szobaSzam, oktatoSzam, targySzam);

        System.out.println("Folytatashoz enter");
        scanner.nextLine();
    }
}

