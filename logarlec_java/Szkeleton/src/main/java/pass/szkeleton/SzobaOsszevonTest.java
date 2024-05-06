package pass.szkeleton;

import pass.model.CustomLogger;
import pass.model.human.Ember;
import pass.model.human.Hallgato;
import pass.model.labyrinth.Ajto;
import pass.model.labyrinth.Labirintus;
import pass.model.labyrinth.Szoba;

import java.util.*;
import java.util.logging.Level;

public class SzobaOsszevonTest {
    public static void test() {
        CustomLogger.suppress();
        Map<String, Szoba> szobaMap = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        Labirintus l = Labirintus.getInstance();
        for (int i = 1; i <= 3; i++) {
            Szoba sz = new Szoba(5, "sz" + i);
            szobaMap.put("sz" + i, sz);
            l.addSzoba(new Szoba(5, "sz" + i));
        }
        List<String> szobaList = new ArrayList<>(szobaMap.keySet());
        Collections.sort(szobaList, Comparator.comparing(String::toString));

        List<Ajto> ajtok = new ArrayList<>();
        CustomLogger.unsuppress();
        String[] szobak;
        boolean invalid;
        do {
            invalid = false;
            System.out.println("Melyik két szoba legyen szomszédos? (2-t válassz space-el elválasztva)");
            CustomLogger.suppress();
            for (String sz : szobaList) {
                System.out.print("[" + sz + "] ");
            }
            szobak = sc.nextLine().split(" ");
            if (szobak.length != 2) {
                CustomLogger.log(Level.WARNING, "Két szobát kell választani!");
                invalid = true;
            } else if (szobak[0].equals(szobak[1])) {
                CustomLogger.log(Level.WARNING, "Két különböző szobát kell választani!");
                invalid = true;
            } else if (!szobaList.contains(szobak[0]) || !szobaList.contains(szobak[1])) {
                CustomLogger.log(Level.WARNING, "Nem létező szoba!");
                invalid = true;
            }
        } while (invalid);

        Szoba sz1 = szobaMap.get(szobak[0]);
        Szoba sz2 = szobaMap.get(szobak[1]);
        Ajto a = new Ajto(sz1, sz2, "a");
        sz1.addAjto(a);
        sz2.addAjto(a);

        for (Szoba sz : szobaMap.values()) {
            System.out.println("Hány ember legyen az " + sz + "-ban?");
            int emberekSzama;
            do {
                emberekSzama = sc.nextInt();
                sc.nextLine();
                if (emberekSzama < 0 || emberekSzama > 5) {
                    CustomLogger.log(Level.WARNING, "Az emberek száma 0 és 5 között lehet!");
                }

            } while (emberekSzama < 0 || emberekSzama > 5);
            for (int i = 1; i <= emberekSzama; i++) {
                Ember e = new Hallgato("h" + i);
                e.masikSzobabaLep(sz);
            }
        }

        CustomLogger.unsuppress();
        do {
            invalid = false;
            System.out.println("Melyik szobákat szeretnéd összevonni? (2-t válassz space-el elválasztva)");
            for (String sz : szobaList) {
                System.out.print("[" + sz + "] (ferohely: " + szobaMap.get(sz).getFerohely() + ", benne levo emberek szama: " + szobaMap.get(sz).getEmberek().size() + ")");
            }
            System.out.append("\n");
            szobak = sc.nextLine().split(" ");
            if (szobak.length != 2) {
                CustomLogger.log(Level.WARNING, "Két szobát kell választani!");
                invalid = true;
            } else if (szobak[0].equals(szobak[1])) {
                CustomLogger.log(Level.WARNING, "Két különböző szobát kell választani!");
                invalid = true;
            } else if (!szobaList.contains(szobak[0]) || !szobaList.contains(szobak[1])) {
                CustomLogger.log(Level.WARNING, "Nem létező szoba!");
                invalid = true;
            }
        } while (invalid);

        sz1 = szobaMap.get(szobak[0]);
        sz2 = szobaMap.get(szobak[1]);
        l.szobakOsszevon(sz1, sz2);

        System.out.println("Folytatashoz enter");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }


}
