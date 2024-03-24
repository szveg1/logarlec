package pass.szkeleton;

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
            System.out.println("4. Ember elájul --> KESZ");
            System.out.println("5. Ájult Ember mozogni próbál --> KESZ");
            System.out.println("6. A hallgató teleportál");
            System.out.println("7. Tranzisztor párosítás");
            System.out.println("8. Szoba osztódás");
            System.out.println("9. Szoba összevonás");
            System.out.println("10. Szoba elgázosítása ---> KÉSZ de tényleg");
            System.out.println("11. Ember belép egy szobába");
            System.out.println("12. Hallgató oktató interakció");
            System.out.println("13. Hallgató védekezik, rongy");
            System.out.println("14. Hallgató védekezik, pohár");
            System.out.println("15. Hallgató védekezik, TVSZ");
            System.out.println("16. Hallgató felvesz egy nem logarléc tárgyat");
            System.out.println("17. Hallgató felveszi a logarlécet ---> KESZ");
            System.out.println("18. Hallgató eldob egy tárgyat");
            System.out.println("19. Oktató felvette a logarlécet");
            System.out.println("20. Oktató felvesz tárgyat, amit nem tud használni");
            System.out.println("21. Oktató eldob egy tárgyat");
            System.out.println("22. Ajtó eltűnik");
            System.out.println("23. Ajtó megjelenik");
            System.out.println("24. Hallgató nem nyitható ajtón próbál átmenni");
            System.out.println("25. Init");
            System.out.println("26. Lejárt az idő");
            System.out.println("0. Kilépés");

            System.out.println("Válasszon egy opciót: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    running = false;
                    System.out.println("Viszontlátás!");
                    break;
                case 1:
                    System.out.println("Ember felvesz tárgyat use-case kiválasztva");
                    break;
                case 2:
                    System.out.println("Ember eldob tárgyat use-case kiválasztva");
                    break;
                case 3:
                    System.out.println("Oktató támad use-case kiválasztva");
                    break;
                case 4:
                    System.out.println("Ember elájul use-case kiválasztva");
                    break;
                case 5:
                    System.out.println("Ájult Ember use-case kiválasztva");
                    break;
                case 6:
                    System.out.println("A hallgató teleportál use-case kiválasztva");
                    break;
                case 7:
                    System.out.println("Tranzisztor párosítás use-case kiválasztva");
                    break;
                case 8:
                    System.out.println("Szoba osztódás use-case kiválasztva");
                    break;
                case 9:
                    System.out.println("Szoba összevonás use-case kiválasztva");
                    break;
                case 10:
                    System.out.println("Szoba elgázosítása use-case kiválasztva");
                    break;
                case 11:
                    System.out.println("Ember belép egy szobába use-case kiválasztva");
                    break;
                case 12:
                    System.out.println("Hallgató oktató interakció use-case kiválasztva");
                    break;
                case 13:
                    System.out.println("Hallgató védekezik, rongy use-case kiválasztva");
                    break;
                case 14:
                    System.out.println("Hallgató védekezik, pohár use-case kiválasztva");
                    break;
                case 15:
                    System.out.println("Hallgató védekezik, TVSZ use-case kiválasztva");
                    break;
                case 16:
                    System.out.println("Hallgató felvesz egy nem logarléc tárgyat use-case kiválasztva");
                    break;
                case 17:
                    System.out.println("Hallgató felveszi a logarlécet use-case kiválasztva");
                    break;
                case 18:
                    System.out.println("Hallgató eldob egy tárgyat use-case kiválasztva");
                    break;
                default:
                    break;

            }
        }
    }
}