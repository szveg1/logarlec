package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;

public class EmberMasikSzobabaLep
{
    private static Szoba sz1 = new Szoba(5, "sz1");

    private static Szoba sz2 = new Szoba(2, "sz2");

    private static Hallgato h = new Hallgato("h");
    private static Oktato o = new Oktato("o");

    private static Ajto a = new Ajto(sz1, sz2, "a");

    private static Maszk m = new Maszk(10, "m");


    private static Map<String, Ember>  emberMap = new HashMap<>();


    public static void setUp() {
        emberMap.put("oktato", o);
        emberMap.put("hallgato", h);
    }

    public static void test(){
        setUp();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hallgató vagy oktató? [hallgato] [oktato]");
        Ember e;
        do {
            String ember = scanner.nextLine();
            e = emberMap.get(ember);
            if (e == null) {
                CustomLogger.log(Level.WARNING, "Nem létező válasz!");
            }
        }while(e == null);

        CustomLogger.suppress();
        e.masikSzobabaLep(sz1);
        CustomLogger.unsuppress();

        System.out.println("Látható az ajtó? [igen] [nem]");

        String valasz;
        do {
            valasz = scanner.nextLine();
            if(!valasz.equals("igen") && !valasz.equals("nem")) {
                CustomLogger.log(Level.WARNING, "Nem létező válasz!");
            }
        } while(!valasz.equals("igen") && !valasz.equals("nem"));

        a.setLathatosag(valasz.equals("igen"));
        a.hasznal(e);

        if(valasz.equals("nem")) return;

        System.out.println("Van-e maszkja az embernek? [igen] [nem]");
        do {
            valasz = scanner.nextLine();
            if(!valasz.equals("igen") && !valasz.equals("nem")) {
                CustomLogger.log(Level.WARNING, "Nem létező válasz!");
            }
        } while(!valasz.equals("igen") && !valasz.equals("nem"));

        if(valasz.equals("igen")){
            CustomLogger.suppress();
            e.targyatFelvesz(m);
            CustomLogger.unsuppress();
            e.tick();
        }

        System.out.println("Mérgező-e a következő szoba? [igen] [nem]");
        do {
            valasz = scanner.nextLine();
            if(!valasz.equals("igen") && !valasz.equals("nem")) {
                CustomLogger.log(Level.WARNING, "Nem létező válasz!");
            }
        } while(!valasz.equals("igen") && !valasz.equals("nem"));

        if(valasz.equals("igen")) {
            CustomLogger.suppress();
            sz2.setPoisonous(10);
            CustomLogger.unsuppress();
            sz2.tick();
        }

        System.out.println("Folytatashoz enter");
        scanner.nextLine();
    }
}
