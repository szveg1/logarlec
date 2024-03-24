package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.*;

public class AjtoTest {

    private static Map<String, Ember>  emberMap = new HashMap<>();

    public static void test() {
        emberMap.put("oktato", new Oktato("o"));
        emberMap.put("hallgato", new Hallgato("h"));

        Ember e = null;
        Scanner scanner = new Scanner(System.in);

        CustomLogger.info("[Hallgató vagy oktató?] ");
        for (String ember : emberMap.keySet()) {
            System.out.print(" [" + ember + "]");
        }
        System.out.append("\n");

        do {
            String ember = scanner.nextLine();
            e = emberMap.get(ember);
            if (e == null) {
                CustomLogger.log(Level.WARNING, "Nem létező ember!");
            }
        } while (e == null);

        Szoba sz1 = new Szoba(8, "sz1");
        Szoba sz2 = new Szoba(8, "sz2");
        e.masikSzobabaLep(sz1);
        Ajto a = new Ajto(sz1, sz2, "a");
        sz1.addAjto(a);
        sz2.addAjto(a);

        CustomLogger.info("[látható vagy nem? true/false] ");
        String s = scanner.nextLine();
        boolean b = Boolean.parseBoolean(s);
        a.setLathatosag(b);

        CustomLogger.info("[nyiljon az ajto a masik szoba fele? true/false] ");
        s = scanner.nextLine();
        boolean b1 = Boolean.parseBoolean(s);

        CustomLogger.info("[nyiljon az ajto erre a szoba fele? true/false] ");
        s = scanner.nextLine();
        boolean b2 = Boolean.parseBoolean(s);

        a.setMerreNyilik(b2, b1);

        a.hasznal(e);
    }
}
