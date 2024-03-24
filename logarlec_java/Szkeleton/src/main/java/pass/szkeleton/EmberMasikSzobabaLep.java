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

    private static Ember e1;
    private static Hallgato h1 = new Hallgato("h1");
    private static Oktato o1 = new Oktato("o1");

    private static Ajto a1 = new Ajto(sz1, sz2);

    private static Maszk m1 = new Maszk(10, "m1");

    private static Map<String, Szoba>  szobampa = new HashMap<>();
    private static Map<String, Ember>  embermpa = new HashMap<>();
    private static Map<String, Ajto>  ajtpmpa = new HashMap<>();
    private static Map<String, Targy>  targympa = new HashMap<>();

    public static void setUp() {
        embermpa.put("oktato", o1);
        embermpa.put("hallgato", h1);
        szobampa.put("kiinduloszoba", sz1);
        szobampa.put("celszoba", sz2);
        ajtpmpa.put("ajto", a1);
        targympa.put("maszk", m1);
    }

    public void test(){
        Scanner scanner = new Scanner(System.in);
        CustomLogger.info("[Hallgató vagy oktató?]\n");
        String ember = scanner.nextLine();
        Ember e = embermpa.get(ember);
        if (e == null) {
            CustomLogger.log(Level.WARNING, "Nem létező válasz!");
        }
        sz1.emberBetesz(e);
        sz2.emberBetesz(e1);
        sz1.emberKivesz(e);
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
}
