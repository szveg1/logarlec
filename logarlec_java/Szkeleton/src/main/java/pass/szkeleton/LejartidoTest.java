package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.Ember;
import pass.model.labyrinth.Ajto;
import pass.model.labyrinth.Labirintus;
import pass.model.labyrinth.Szoba;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LejartidoTest {
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
    }


}
