package pass.szkeleton;

import pass.model.human.Ember;
import pass.model.human.Hallgato;

import java.util.Scanner;

public class Menu {

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Válasszon a következő use-case-ek közül:");
            System.out.println("1. Ember felvesz tárgyat ---> KESZ");
            System.out.println("2. Ember eldob tárgyat ---> KESZ");
            System.out.println("3. Oktató támad ---> KESZ");
            System.out.println("4. Ember  gázos szoba interakció  --> KESZ");
            System.out.println("5. A hallgató teleportál");
            System.out.println("6. Szoba osztódás");
            System.out.println("7. Szoba összevonás --> KÉSZ");
            System.out.println("8. Szoba elgázosítása ---> KÉSZ de tényleg");
            System.out.println("9. Ember belép egy szobába");
            System.out.println("10. Hallgató oktató interakció");
            System.out.println("11. Hallgató felvesz egy nem logarléc tárgyat");
            System.out.println("12. Hallgató felveszi a logarlécet ---> KESZ de tényleg");
            System.out.println("19. Oktató felvette a logarlécet");
            System.out.println("20. Oktató felvesz tárgyat, amit nem tud használni");
            System.out.println("22. Ajtó eltűnik");
            System.out.println("23. Ajtó megjelenik");
            System.out.println("24. Ember ajtó interakció");
            System.out.println("25. Init");
            System.out.println("26. Lejárt az idő");
            System.out.println("0. Kilépés");

            System.out.println("Válasszon egy opciót: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    running = false;
                    System.out.println("Viszontlátás!");
                    break;
                case 1:
                    System.out.println("Ember felvesz tárgyat use-case kiválasztva");
                    TargyFelveszTest.test();
                    break;
                case 2:
                    System.out.println("Ember eldob tárgyat use-case kiválasztva");
                    TargyEldobTest.test();
                    break;
                case 3:
                    System.out.println("Oktató támad use-case kiválasztva");
                    OktatoTamadTest.test();
                    break;
                case 4:
                    System.out.println("4. Ember  belép gázos szobába és elájul," +
                            "majd megpróbál mozogni vagy tárgyat felvenni");
                    EmberElajulTest.test();
                    break;
                case 5:
                    System.out.println("A hallgató teleportál use-case kiválasztva");
                    HallgatoTeleportTest.test();
                    break;
                case 6:
                    System.out.println("Szoba osztódás use-case kiválasztva");
                    SzobaFelosztTest.test();
                    break;
                case 7:
                    System.out.println("Szoba összevonás use-case kiválasztva");
                    SzobaOsszevonTest.test();
                    break;
                case 8:
                    System.out.println("Szoba elgázosítása use-case kiválasztva");
                    szobaelgazosit.test();
                    break;
                case 9:
                    System.out.println("Ember belép egy szobába use-case kiválasztva");
                    EmberMasikSzobabaLep.test();
                    break;
                case 12:
                    System.out.println("Hallgató oktató interakció use-case kiválasztva");
                    break;
                case 16:
                    System.out.println("Hallgató felvesz egy nem logarléc tárgyat use-case kiválasztva");
                    break;
                case 17:
                    System.out.println("Hallgató felveszi a logarlécet use-case kiválasztva");
                    break;
                case 24:
                    System.out.println("Ember ajtó interakció use-case kiválasztva");
                    AjtoTest.test();
                    break;
                case 25:
                    System.out.println("Init use-case kiválasztva");
                    InitTest.initTest();
                    break;
                case 26:
                    System.out.println("Lejárt az idő use-case kiválasztva");
                    LejartidoTest.test();
                default:
                    break;

            }
        }
    }
}