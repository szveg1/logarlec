package pass.szkeleton;


import pass.model.CustomLogger;
import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;


public class OktatoTamadTest {
    private static Labirintus labirintus;
    private static Szoba szoba;
    private static Oktato oktato;
    private static Hallgato hallgato;
    private static Map<String, Oktato>  oktatoMap = new HashMap<>();
    private static Map<String, Targy> targyMap = new HashMap<>();
    public static void init_test(int oktatoSzam, String hallgatoVedekezik) {
        labirintus = new Labirintus("l");
        szoba = new Szoba(5, "sz");
        hallgato = new Hallgato("h");
        Rongy rongy = new Rongy("r");
        Pohar pohar = new Pohar("p");
        TVSZ tvsz = new TVSZ("t");

        targyMap.put("Rongy", rongy);
        targyMap.put("Pohar", pohar);
        targyMap.put("TVSZ", tvsz);

        szoba.addItem(rongy);
        szoba.addItem(pohar);
        szoba.addItem(tvsz);

        if (!targyMap.containsKey(hallgatoVedekezik)) {
            CustomLogger.log(Level.WARNING, "Ismeretlen targy: " + hallgatoVedekezik);
            szoba.emberBetesz(hallgato);
        }else if(hallgatoVedekezik.equals("")){
            CustomLogger.info("Nem vedekezik a hallgato.");
            szoba.emberBetesz(hallgato);
        }else{
            hallgato.masikSzobabaLep(szoba);
            hallgato.targyatFelvesz(targyMap.get(hallgatoVedekezik));
        }

            for (int i = 1; i <= oktatoSzam; i++) {
                oktato = new Oktato("o"+i);
                oktato.masikSzobabaLep(szoba);
                oktatoMap.put("Oktato" + i, oktato);
            }

    }


    public static void test() {
        Scanner scanner = new Scanner(System.in);
        CustomLogger.info("Mivel védekezik a hallgató?");
        CustomLogger.info("[Rongy, Pohar, TVSZ]");
        String hallgatoVedekezik = scanner.nextLine();

        CustomLogger.info("Hány oktató legyen a szobában (ferohely: 5)?");
        int oktatoSzam = scanner.nextInt();
        if(oktatoSzam < 5){
            init_test(oktatoSzam, hallgatoVedekezik);
        } else if (oktatoSzam >= 5) {
            CustomLogger.log(Level.WARNING, "Csak 4 oktato tudott bemenni.");
            init_test(4, hallgatoVedekezik);
        }

        for(int i = 1; i <= oktatoSzam; i++){
            CustomLogger.info("Oktato" + i + " tamadasa");
            oktatoMap.get("Oktato" + i).hallgatotMegtamad(hallgato);
            if(hallgato.getEletbenVan()){
                CustomLogger.info("A hallgato tulelte az " + i + ". oktato tamadasat.");
            } else {
                CustomLogger.info("A hallgato meghalt az " + i + ". oktato tamadasaban.");
                break;
            }
        }
        if(hallgato.getEletbenVan()){
            CustomLogger.info("A hallgato tulelte az osszes oktato tamadasat.");
        }
    }
}



