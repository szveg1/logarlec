package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.*;
import pass.model.item.*;
import pass.model.labyrinth.*;

import java.util.*;

public class SzobaOsszevonTest {
    public static void test() {
        Map<String, Szoba> szobaMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        Labirintus l = new Labirintus("l");
        for (int i = 1; i <= 3; i++) {
            Szoba sz = new Szoba(5, "sz" + i);
            szobaMap.put("sz" + i, sz);
            l.addSzoba(new Szoba(5, "sz" + i));
        }

        List<Ajto> ajtok = new ArrayList<>();
        CustomLogger.info("Melyik két szoba legyen szomszédos? (2-t válassz space-el elválasztva)");
        for(String sz : szobaMap.keySet()){
            System.out.print("[" + sz + "] ");
        }
        String[] szobak = sc.nextLine().split(" ");
        Szoba sz1 = szobaMap.get(szobak[0]);
        Szoba sz2 = szobaMap.get(szobak[1]);
        Ajto a = new Ajto(sz1, sz2, "a");
        sz1.addAjto(a);
        sz2.addAjto(a);

//
//        for(Szoba sz : l.getSzobak()){
//            for(Ajto a : ajtok){
//                if(a.getSzomszed(sz) != null)
//                    CustomLogger.info(sz + " szomszédos " + a.getSzomszed(sz) + "-val");
//            }
//        }

        CustomLogger.info("Melyik szobákat szeretnéd összevonni? (2-t válassz space-el elválasztva)");
        for(String sz : szobaMap.keySet()){
            System.out.print("[" + sz + "] ");
        }
        System.out.append("\n");
        szobak = sc.nextLine().split(" ");
        sz1 = szobaMap.get(szobak[0]);
        sz2 = szobaMap.get(szobak[1]);
        l.szobakOsszevon(sz1, sz2);

    }


}
