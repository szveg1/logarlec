package pass.szkeleton;

import java.util.Scanner;

public class Menu {

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Válassz a következő use-case-ek közül:");
            System.out.println("1. Ember felvesz tárgyat");
            System.out.println("2. Ember eldob tárgyat");
            System.out.println("3. Oktató támad");
            System.out.println("4. Ember gázos szoba interakció");
            System.out.println("5. A hallgató teleportál");
            System.out.println("6. Szoba osztódás");
            System.out.println("7. Szoba összevonás");
            System.out.println("8. Szoba elgázosítása");
            System.out.println("9. Ember belép egy szobába");
            System.out.println("10. Ajtó eltűnik / megjelenik");
            System.out.println("11. Ember ajtó interakció");
            System.out.println("12. Init");
            System.out.println("13. Lejárt az idő");
            System.out.println("0. Kilépés");

            System.out.println("Válassz egy opciót: ");
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
                    System.out.println("Ember gázos szoba interakció use-case kiválasztva");
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
                    SzobaElgazositTest.test();
                    break;
                case 9:
                    System.out.println("Ember belép egy szobába use-case kiválasztva");
                    EmberMasikSzobabaLep.test();
                    break;
                case 10:
                    System.out.println("Ajtó eltűnik / megjelenik use-case kiválasztva");
                    AjtoEltunikMegjelenikTest.test();
                    break;
                case 11:
                    System.out.println("Ember ajtó interakció use-case kiválasztva");
                    AjtoHasznalatTest.test();
                    break;
                case 12:
                    System.out.println("Init use-case kiválasztva");
                    InitTest.initTest();
                    break;
                case 13:
                    System.out.println("Lejárt az idő use-case kiválasztva");
                    IdoLejartTest.test();
                    break;
                default:
                    break;

            }
        }
    }
}