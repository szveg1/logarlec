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
        CustomLogger.suppress();
        labirintus = Labirintus.getInstance();
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
            CustomLogger.log(Level.WARNING, "Ismeretlen tárgy: " + hallgatoVedekezik + ". A hallgató nem védekezik így.");
            szoba.emberBetesz(hallgato);
        }else if(hallgatoVedekezik.equals("")){
            CustomLogger.log(Level.WARNING, "Nem választottál védekezési módot. A hallgató nem védekezik így.");
            szoba.emberBetesz(hallgato);
        }else{
            hallgato.masikSzobabaLep(szoba);
            hallgato.targyatFelvesz(targyMap.get(hallgatoVedekezik));
        }

            for (int i = 1; i <= oktatoSzam; i++) {
                oktato = new Oktato("o"+i);
                oktato.masikSzobabaLep(szoba);
                oktatoMap.put("Oktató" + i, oktato);
            }
        CustomLogger.unsuppress();
    }


    public static void test() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mivel védekezik a hallgató?");
        System.out.println("[Rongy] [Pohar] [TVSZ] [Semmi (Enter)]");
        String hallgatoVedekezik = scanner.nextLine();

        System.out.println("Hány oktató legyen a szobában (férőhely: 5)?");
        int oktatoSzam = scanner.nextInt();
        if(oktatoSzam < 5){
            init_test(oktatoSzam, hallgatoVedekezik);
        } else if (oktatoSzam >= 5) {
            oktatoSzam = 4;
            CustomLogger.log(Level.WARNING, "Csak 4 oktató tudott bemenni.");
            init_test(oktatoSzam, hallgatoVedekezik);
        }

        for(int i = 1; i <= oktatoSzam; i++){
            CustomLogger.info("Oktato" + i + " támadása");
            oktatoMap.get("Oktató" + i).hallgatotMegtamad(hallgato);
            if(hallgato.getEletbenVan()){
                CustomLogger.info("A hallgató túlélte az " + i + ". oktató támadását.");
            } else {
                CustomLogger.info("A hallgató meghalt az " + i + ". oktató tamadásaban.");
                break;
            }
        }
        if(hallgato.getEletbenVan()){
            CustomLogger.info("A hallgató túlélte az összes oktato tamadását.");
        }

        System.out.println("Folytatashoz enter");
        scanner.nextLine();
    }
}



