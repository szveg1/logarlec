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

    private static Map<String, Szoba> szobaMap = new HashMap<>();
    private static Map<String, Ember>  emberMap = new HashMap<>();
    private static Map<String, Ajto>  ajtoMap = new HashMap<>();
    private static Map<String, Targy>  targympa = new HashMap<>();

    public static void setUp() {
        emberMap.put("oktato", o);
        emberMap.put("hallgato", h);
        szobaMap.put("kiinduloszoba", sz1);
        szobaMap.put("celszoba", sz2);
        ajtoMap.put("ajto", a);
        targympa.put("maszk", m);
    }

    public static void test(){
        setUp();
        Scanner scanner = new Scanner(System.in);
        CustomLogger.info("[Hallgató vagy oktató?]\n");
        String ember = scanner.nextLine();
        Ember e = emberMap.get(ember);
        if (e == null) {
            CustomLogger.log(Level.WARNING, "Nem létező válasz!");
        }

        sz1.emberBetesz(e);
        CustomLogger.info("[Látható az ajtó? Igen vgay nem?]\n");
        String qwerty = scanner.nextLine();
        if (qwerty.equals("igen")) {
            a.setLathatosag(true);
            a.hasznal(e);
            if(e.getJelenlegiSzoba()==sz2){
                CustomLogger.info("sikeresen szobát váltott");
            }
            else if(e.getJelenlegiSzoba()==sz1){
                CustomLogger.info("valami nem stimmel");
            }

            CustomLogger.info("[mérgező-e a következő szoba? Igen vagy nem?]\n");
            String asd = scanner.nextLine();
            if(asd.equals("igen")){
                sz2.setPoisonous(10);
            }
            else if(asd.equals("nem")){
                sz2.setPoisonous(10);
            }
            else{
                CustomLogger.log(Level.WARNING, "Nem létező válasz!");
            }
            CustomLogger.info("[Van-e maszkja az embernek? Igen vagy nem?]\n");
            String asd1 = scanner.nextLine();
            if(asd1.equals("igen")){
                e.setGazEllenVedett(true);
                if (!e.getAjult()) CustomLogger.info("ember elajult");
                else CustomLogger.info("valami nem stimmel");
            }
            else if(asd1.equals("nem")){
                e.ajulas();
                if (e.getAjult()) CustomLogger.info("ember elajult");
                else CustomLogger.info("valami nem stimmel");
            }
            else{
                CustomLogger.log(Level.WARNING, "Nem létező válasz!");
            }
        }
        else if (qwerty.equals("nem")) {
            CustomLogger.log(Level.WARNING, "Nincs kijarat!");
        }
        else{
            CustomLogger.log(Level.WARNING, "Nem létező válasz!");
        }
    }
}
