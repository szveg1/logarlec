package pass.szkeleton;

import java.util.Scanner;
import java.util.logging.Level;

import pass.model.CustomLogger;
import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

public class SzobaFelosztTest {
    public static void test() {
        Labirintus labirintus = new Labirintus("l");
        Scanner scanner = new Scanner(System.in);

        CustomLogger.info("Mekkora legyen a szoba ferohelye?");
        String s = scanner.nextLine();
        int m = Integer.parseInt(s);

        Szoba sz1 = new Szoba(m, "sz1");
        labirintus.addSzoba(sz1);

        CustomLogger.info("Hany ember legyen a szobaban?");
        s = scanner.nextLine();
        m = Integer.parseInt(s);
        for (int i=0; i < m; i++) {
            Ember e = new Hallgato("h"+i);
            e.masikSzobabaLep(sz1);
        }

        CustomLogger.info("Hany targy legyen a szobaban?");
        s = scanner.nextLine();
        scanner.close();
        m = Integer.parseInt(s);
        for (int i=0; i < m; i++) {
            sz1.addItem(new Rongy("r"+i));
        }

        labirintus.szobaFeloszt(sz1);

        if (labirintus.getSzobak().size() == 2) CustomLogger.info("Letrejott egy uj szoba");
        else CustomLogger.log(Level.WARNING, "Nem jott letre uj szoba");

        if (labirintus.getSzobak().get(0).getFerohely() == labirintus.getSzobak().get(1).getFerohely()) CustomLogger.info("Letrejott szoba ferohelye egyenlo a regivel");
        else CustomLogger.log(Level.WARNING, "Letrejott szoba ferohelye nem egyenlo a regivel");

        if (Math.abs(labirintus.getSzobak().get(0).getEmberek().size() - labirintus.getSzobak().get(1).getEmberek().size()) <= 1) CustomLogger.info("Letrejott szobaba az emberek fele kerult");
        else CustomLogger.log(Level.WARNING, "Letrejott szobaba baj van az emberek szamaval");

        if (Math.abs(labirintus.getSzobak().get(0).getItems().size() - labirintus.getSzobak().get(1).getItems().size()) <= 1) CustomLogger.info("Letrejott szobába a targyak fele kerult");
        else CustomLogger.log(Level.WARNING, "Letrejött szobaba baj van az targyak szamaval");



    }
}
