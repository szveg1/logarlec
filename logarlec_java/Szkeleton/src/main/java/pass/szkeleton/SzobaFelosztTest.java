package pass.szkeleton;

import java.util.Scanner;
import java.util.logging.Level;

import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

public class SzobaFelosztTest {
    public void test() {
        Labirintus labirintus = new Labirintus();
        Scanner scanner = new Scanner(System.in);

        Main.logger.info("Mekkora legyen a szoba ferohelye?");
        String s = scanner.nextLine();
        int m = Integer.parseInt(s);

        Szoba sz1 = new Szoba(m);
        labirintus.addSzoba(sz1);

        Main.logger.info("Hany ember legyen a szobaban?");
        s = scanner.nextLine();
        m = Integer.parseInt(s);
        for (int i=0; i < m; i++) {
            sz1.emberBetesz(new Hallgato("h"+i));
        }

        Main.logger.info("Hany targy legyen a szobaban?");
        s = scanner.nextLine();
        scanner.close();
        m = Integer.parseInt(s);
        for (int i=0; i < m; i++) {
            sz1.addItem(new Rongy());
        }

        labirintus.szobaFeloszt(sz1);

        if (labirintus.getSzobak().size() == 2) Main.logger.info("Letrejott egy uj szoba");
        else Main.logger.log(Level.WARNING, "Nem jott letre uj szoba");

        if (labirintus.getSzobak().get(0).getFerohely() == labirintus.getSzobak().get(1).getFerohely()) Main.logger.info("Letrejott szoba ferohelye egyenlo a regivel");
        else Main.logger.log(Level.WARNING, "Letrejott szoba ferohelye nem egyenlo a regivel");

        if (labirintus.getSzobak().get(0).getEmberek().size() - labirintus.getSzobak().get(1).getEmberek().size() <= 1) Main.logger.info("Letrejott szobaba az emberek fele kerult");
        else Main.logger.log(Level.WARNING, "Letrejott szobaba baj van az emberek szamaval");

        if (labirintus.getSzobak().get(0).getItems().size() - labirintus.getSzobak().get(1).getItems().size() <= 1) Main.logger.info("Letrejott szobába a targyak fele kerult");
        else Main.logger.log(Level.WARNING, "Letrejött szobaba baj van az targyak szamaval");



    }
}
