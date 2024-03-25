package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.labyrinth.Ajto;
import pass.model.labyrinth.Szoba;

import java.util.*;
import java.util.logging.Level;

public class AjtoEltunikMegjelenikTest {

    private static Szoba sz1 = new Szoba(5, "sz1");
    private static Szoba sz2 = new Szoba(2, "sz2");
    private static Ajto a1 = new Ajto(sz1, sz2, "a1");

    public static void test()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Látható az ajtó? [igen] [nem]");
        String valasz;
        do{
            valasz = scanner.nextLine();
            if(!valasz.equals("igen") && !valasz.equals("nem")){
                CustomLogger.log(Level.WARNING, "Nem létező válasz!");
            }
        } while(!valasz.equals("igen") && !valasz.equals("nem"));

        a1.setLathatosag(valasz.equals("igen"));

        System.out.println("Változzon a láthatóság? [igen] [nem]");
        do{
            valasz = scanner.nextLine();
            if(!valasz.equals("igen") && !valasz.equals("nem")){
                CustomLogger.log(Level.WARNING, "Nem létező válasz!");
            }
        } while(!valasz.equals("igen") && !valasz.equals("nem"));

        if(valasz.equals("igen")){
            a1.lathatosagValtoztass();
        }


        System.out.println("Folytatashoz enter");
        scanner.nextLine();
    }
}
