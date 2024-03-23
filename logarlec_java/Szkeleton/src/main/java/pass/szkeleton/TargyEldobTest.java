package pass.szkeleton;

import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.util.*;

public class TargyEldobTest {
    public static void setUp(){
        Szoba sz = new Szoba(1);
        Ember h = new Hallgato();
        Ember o = new Oktato();
        Targy r = new Rongy();
        sz.emberBetesz(h);
        sz.emberBetesz(o);
        sz.addItem(r);
        h.targyatFelvesz(r);
    }

    public void test(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hallgató vagy oktató?");
        String ember = scanner.nextLine();

    }
}
