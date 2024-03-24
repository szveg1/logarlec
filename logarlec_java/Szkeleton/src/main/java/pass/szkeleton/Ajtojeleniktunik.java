package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.Ember;
import pass.model.labyrinth.Ajto;
import pass.model.labyrinth.Szoba;

import java.util.HashMap;
import java.util.Map;

import java.util.*;
import java.util.logging.Level;

public class Ajtojeleniktunik {

    private static Szoba sz1 = new Szoba(5, "sz1");
    private static Szoba sz2 = new Szoba(2, "sz2");
    private static Ajto a1 = new Ajto(sz1, sz2, "a1");

    public static void test()
    {
        Scanner scanner = new Scanner(System.in);
        CustomLogger.info("[latható az ajto? Igen vagy nem?]\n");
        String asd = scanner.nextLine();
        if(asd.equals("igen")){
            a1.setLathatosag(true);
        }
        else if(asd.equals("nem")){
            a1.setLathatosag(false);
        }
        else{
            CustomLogger.log(Level.WARNING, "Nem létező válasz!");
        }

        CustomLogger.info("[valtozzon a láthatosag? Igen vagy nem?]\n");
        String asd2 = scanner.nextLine();
        if(asd2.equals("igen")){
            a1.lathatosagValtoztass();
        }
        else if (!asd2.equals("igen") && !asd2.equals("nem")) {
            CustomLogger.log(Level.WARNING, "Nem létező válasz!");
        }

        if(asd.equals("igen") && asd2.equals("igen")){
            if (!a1.getLathatosag()){
                CustomLogger.info("valami stimmel");
            }
            else {
                CustomLogger.info("valami nem stimmel");
            }
        }
        else if(asd.equals("nem") && asd2.equals("igen")){
            if (a1.getLathatosag()){
                CustomLogger.info("valami stimmel");
            }
            else {
                CustomLogger.info("valami nem stimmel");
            }
        }
        else if(asd.equals("igen") && asd2.equals("nem")){
            if (a1.getLathatosag()){
                CustomLogger.info("valami stimmel");
            }
            else {
                CustomLogger.info("valami nem stimmel");
            }
        }
        else if(asd.equals("nem") && asd2.equals("nem")){
            if (!a1.getLathatosag()){
                CustomLogger.info("valami stimmel");
            }
            else {
                CustomLogger.info("valami nem stimmel");
            }
        }
    }
}
