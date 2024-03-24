package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.Ember;
import pass.model.labyrinth.Ajto;
import pass.model.labyrinth.Szoba;

import java.util.HashMap;
import java.util.Map;

import java.util.*;

public class Ajtojeleniktunik {

    private static Szoba sz1 = new Szoba(5, "sz1");
    private static Szoba sz2 = new Szoba(2, "sz2");
    private static Ajto a1 = new Ajto(sz1, sz2);


    private static Map<String, Szoba> szobampa = new HashMap<>();
    private static Map<String, Ember>  embermpa = new HashMap<>();
    private static Map<String, Ajto>  ajtpmpa = new HashMap<>();

    public static void setUp() {
        szobampa.put("kiinduloszoba", sz1);
        szobampa.put("celszoba", sz2);
        ajtpmpa.put("ajto", a1);
    }


    public void test()
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

        CustomLogger.info("[valtozzon a láthatosag? Igen vagy nem?]\n");
        String asd2 = scanner.nextLine();
        if(asd2.equals("igen")){
            a1.lathatosagValtoztass();
        }

        if(asd.equals("igen") && asd2.equals("igen")){
            if (!a1.getLathatosag()){
                CustomLogger.info("valami stimmel");
            }
            else if (a1.getLathatosag()){
                CustomLogger.info("valami nem stimmel");
            }
        }
        else if(asd.equals("nem") && asd2.equals("igen")){
            if (a1.getLathatosag()){
                CustomLogger.info("valami stimmel");
            }
            else if (!a1.getLathatosag()){
                CustomLogger.info("valami nem stimmel");
            }
        }
        else if(asd.equals("igen") && asd2.equals("nem")){
            if (a1.getLathatosag()){
                CustomLogger.info("valami stimmel");
            }
            else if (!a1.getLathatosag()){
                CustomLogger.info("valami nem stimmel");
            }
        }
        else if(asd.equals("nem") && asd2.equals("nem")){
            if (!a1.getLathatosag()){
                CustomLogger.info("valami stimmel");
            }
            else if (a1.getLathatosag()){
                CustomLogger.info("valami nem stimmel");
            }
        }
    }
}
