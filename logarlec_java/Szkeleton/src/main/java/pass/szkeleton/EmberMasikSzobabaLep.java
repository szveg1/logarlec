package pass.szkeleton;

import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmberMasikSzobabaLep
{
    private static Szoba sz1 = new Szoba(5);

    private static Szoba sz2 = new Szoba(2);

    private static Ember e1;
    private static Hallgato h1 = new Hallgato();
    private static Oktato o1 = new Oktato();

    private static Ajto a1 = new Ajto(sz1, sz2);

    private static Maszk m1 = new Maszk(10);

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
        System.out.print("[Hallgató vagy oktató?]\n");
        String ember = scanner.nextLine();
        Ember e = embermpa.get(ember);
        sz1.addEmber(e);
        sz2.emberBetesz(e1);
        sz1.emberKivesz(e);

        System.out.print("[mérgező-e a következő szoba? Igen vagy nem?]\n");
        String asd = scanner.nextLine();
        if(asd.equals("igen")){
            sz2.setPoisonous(10);
        }
        System.out.print("[Van-e maszkja az embernek? Igen vagy nem?]\n");
        String asd1 = scanner.nextLine();
        if(asd1.equals("igen")){
            e.setGazEllenVedett(true);
            if (!e.getAjult()) System.out.println("ember elajult");
            else System.out.println("valami nem stimmel");
        }
        else if(asd1.equals("nem")){
            e.ajulas();
            if (e.getAjult()) System.out.println("ember elajult");
            else System.out.println("valami nem stimmel");

        }
    }
}
