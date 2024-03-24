package pass.szkeleton;


import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;


public class Oktato_tamad {
    private static Labirintus labirintus;
    private static Szoba szoba;
    private static Oktato oktato;
    private static Hallgato hallgato;
    private static Map<String, Oktato>  oktatoMap = new HashMap<>();
    private static Map<String, Targy> targyMap = new HashMap<>();
    public static void init_test(int oktatoSzam, String hallgatoVedekezik) {
        labirintus = new Labirintus();
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
            Szkeleton.logger.log(Level.WARNING, "Ismeretlen targy: " + hallgatoVedekezik);
            szoba.emberBetesz(hallgato);
        }else if(hallgatoVedekezik.equals("")){
            Szkeleton.logger.info("Nem vedekezik a hallgato.");
            szoba.emberBetesz(hallgato);
        }else{
            szoba.emberBetesz(hallgato);
            hallgato.targyatFelvesz(targyMap.get(hallgatoVedekezik));
        }

            for (int i = 0; i < oktatoSzam; i++) {
                oktato = new Oktato("o"+i);
                szoba.emberBetesz(oktato);
                oktatoMap.put("Oktato" + i, oktato);
            }

            //szoba.emberBetesz(hallgato);
            //emberMap.put("Hallgato", hallgato);
    }


    public static void Oktato_tamad_test() {
        Scanner scanner = new Scanner(System.in);
        Szkeleton.logger.info("Mivel védekezik a hallgató?");
        Szkeleton.logger.info("[Rongy, Pohar, TVSZ]");
        String hallgatoVedekezik = scanner.nextLine();

        Szkeleton.logger.info("Hány oktató legyen a szobában (ferohely: 5)?");
        int oktatoSzam = scanner.nextInt();
        if(oktatoSzam < 5){
            init_test(oktatoSzam, hallgatoVedekezik);
        } else if (oktatoSzam >= 5) {
            Szkeleton.logger.log(Level.WARNING, "Csak 4 oktato tudott bemenni.");
            init_test(4, hallgatoVedekezik);
        }

        for(int i = 0; i < oktatoSzam; i++){
            Szkeleton.logger.info("Oktato" + i + " tamadasa");
            oktatoMap.get("Oktato" + i).hallgatotMegtamad(hallgato);
            if(hallgato.getEletbenVan()){
                Szkeleton.logger.info("A hallgato tulelte az " + i + ". oktato tamadasat.");
            } else {
                Szkeleton.logger.info("A hallgato meghalt az " + i + ". oktato tamadasaban.");
                break;
            }
        }
        if(hallgato.getEletbenVan()){
            Szkeleton.logger.info("A hallgato tulelte az osszes oktato tamadasat.");
        }
    }
}



