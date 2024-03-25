package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.labyrinth.Labirintus;

import java.util.Scanner;

public class IdoLejartTest {
    private static Labirintus l1 = new Labirintus("l1");

    public static void test(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("[Mennyi ideig tart még a játék?]");
        String ido = scanner.nextLine();
        int timeLeft = Integer.parseInt(ido);
        l1.setTimeLeft(timeLeft);
        CustomLogger.info("A játékból még " + timeLeft + " kör van hátra.");
        for(int i = 0; i < timeLeft; i++){
            l1.tick();
        }
        System.out.println("Folytatashoz enter");
        scanner.nextLine();
    }


}
