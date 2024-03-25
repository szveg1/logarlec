package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.*;
import pass.model.labyrinth.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.*;

public class AjtoHasznalatTest {

    private static Map<String, Ember>  emberMap = new HashMap<>();
    private static Map<String, Boolean> choiceMap = new HashMap<>();

    public static void test() {
        emberMap.put("oktato", new Oktato("o"));
        emberMap.put("hallgato", new Hallgato("h"));
        choiceMap.put("igen", true);
        choiceMap.put("nem", false);

        Ember e = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("[Hallgató vagy oktató?] ");
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

        CustomLogger.suppress();
        Szoba sz1 = new Szoba(8, "sz1");
        Szoba sz2 = new Szoba(8, "sz2");
        e.masikSzobabaLep(sz1);
        Ajto a = new Ajto(sz1, sz2, "a");
        sz1.addAjto(a);
        sz2.addAjto(a);
        CustomLogger.unsuppress();

        System.out.println("[látható vagy nem?]" + "\n" + "[igen] [nem]");
        Boolean choice = null;
        do {
            String choiceS = scanner.nextLine();
            choice = choiceMap.get(choiceS);
            if (choice == null) {
                CustomLogger.log(Level.WARNING, "Nem jól írtad le!");
            }
        } while (choice == null);
        a.setLathatosag(choice);

        System.out.println("[nyiljon az ajto a masik szoba fele?]" + "\n" + "[igen] [nem]");
        Boolean choice2 = null;
        do {
            String choiceS = scanner.nextLine();
            choice2 = choiceMap.get(choiceS);
            if (choice2 == null) {
                CustomLogger.log(Level.WARNING, "Nem jól írtad le!");
            }
        } while (choice2 == null);

        System.out.println("[nyiljon az ajto erre a szoba fele?]" + "\n" + "[igen] [nem]");
        Boolean choice3 = null;
        do {
            String choiceS = scanner.nextLine();
            choice3 = choiceMap.get(choiceS);
            if (choice3 == null) {
                CustomLogger.log(Level.WARNING, "Nem jól írtad le!");
            }
        } while (choice3 == null);
        a.setMerreNyilik(choice3, choice2);

        a.hasznal(e);

        System.out.println("Folytatashoz enter");
        scanner.nextLine();
    }
}

