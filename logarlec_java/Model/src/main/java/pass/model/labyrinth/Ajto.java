package pass.model.labyrinth;

import pass.model.CustomLogger;
import pass.model.human.Ember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Az Ajtó osztály felelős az egyes szobákat összekötő ajtók kezeléséért és
működtetéséért a labirintusban. Feladata az ajtók állapotának nyomon követése,
valamint annak ellenőrzése, hogy nyitható-e a kért irányba az ajtó. Az Ajtó osztály
biztosítja a játékosok számára az átjutást a különböző szobák között azáltal, hogy
lehetővé teszi az áthaladást az ajtókon keresztül.  */
public class Ajto {
    private Szoba egyikOldal;
    private Szoba masikOldal;
    private boolean lathato = true;
    private Map<Szoba, Boolean> merrolNyilik = new HashMap<>();
    private String nev;

    /**
     * @param egyikOldal - Az egyik oldalán lévő szoba
     * @param masikOldal - A másik oldalán lévő szoba
     * @param nev        - Szkeleton érdekében logoláshoz
     */
    public Ajto(Szoba egyikOldal, Szoba masikOldal, String nev) {
        this.egyikOldal = egyikOldal;
        this.masikOldal = masikOldal;
        merrolNyilik.put(egyikOldal, false);
        merrolNyilik.put(masikOldal, false);
        this.nev = nev;
    }

    /**
     * Getter fügvény ami visszaadja az ajtó két oldalán lévő szobák neveit
     *
     * @return List<String>, a szobák neveit tároló lista
     */
    public List<String> getOldalak() {
        List<String> oldalak = new ArrayList<>();
        oldalak.add(egyikOldal.getNev());
        oldalak.add(masikOldal.getNev());
        return oldalak;
    }

    /**
     * A függvény kiírja az objektum nevét
     *
     * @return String, Szkeleton kiiratashoz
     */
    public String toString() {
        return nev + " :Ajto";
    }

    /**
     * Getter fügvény ami visszaadja az ajtó nevét
     *
     * @return String, az ajtó neve
     */
    public String getNev() {
        return nev;
    }

    /**
     * A függvény ha látható az ajtó akkor azpn keresztül a következő
     * szobába lépteti az embert és a kiinduló szobából eltávolítja
     *
     * @param e - Az ember aki át kíván lépni a másik szobába, ez felelős a mozgatásáért.
     */
    public void hasznal(Ember e) {
        if (e.getJelenlegiSzoba() != egyikOldal && e.getJelenlegiSzoba() != masikOldal) {
            System.out.println("ezt az ajtot nem tudod ebbol a szobabol hasznalni");
            return;
        }
        if (!lathato) {
            System.out.println(e + " nem latja " + this + "-t, nem tud atmenni.");
            return;
        }
        Szoba jelenlegiSzoba = e.getJelenlegiSzoba();
        Szoba hovaMegy = (egyikOldal == jelenlegiSzoba) ? masikOldal : egyikOldal;
        if (merrolNyilik.get(jelenlegiSzoba)) {
            e.masikSzobabaLep(hovaMegy);
            //jelenlegiSzoba.emberKivesz(e);
        }
    }

    /**
     * Setter függvény ami beállítja hogy merre nyílhat az ajtó
     *
     * @param b1 Az egyik irányba nyílik-e
     * @param b2 A másik irányba nyílik-e
     */
    public void setMerreNyilik(boolean b1, boolean b2) {
        merrolNyilik.clear();
        merrolNyilik.put(egyikOldal, b1);
        merrolNyilik.put(masikOldal, b2);
    }

    /**
     * Getter függvény ami visszaadja hogy látható-e az ajtó
     *
     * @return láthatóságot visszaadja
     */
    public boolean getLathatosag() {
        return lathato;
    }

    /**
     * Setter függvény ami beállítja hogy látható-e az ajtó
     *
     * @param a beállítja a láthatóságot
     */
    public void setLathatosag(boolean a) {
        lathato = a;
    }

    /**
     * Ha az ajtó egyik oldalán egy átkozott szoba található
     * a szoba láthatósága változik
     */
    public void villogas() {
        if (!egyikOldal.atkozottE() && !masikOldal.atkozottE()) {
            return;
        }
        if (Labirintus.getInstance().getTimeLeft() % 10 == 0)
            lathato = false;
        else if (Labirintus.getInstance().getTimeLeft() % 10 == 5)
            lathato = true;
    }


    /**
     * Afüggvény megváltoztatja a láthatóságát az ajtónak
     */
    public void lathatosagValtoztass() {
        if (lathato) {
            lathato = false;
            CustomLogger.info(this + " láthatatlan lett.");
        } else {
            lathato = true;
            CustomLogger.info(this + " láthatóvá vált.");
        }
    }

    /**
     * Getter függvény ami visszadja a megadott szobából,
     * ezen az ajtón keresztül elérhető szomszédját
     *
     * @param sz - Egy adott szoba.
     * @return Megadja egy adott ajtó másik oldalán lévő szobát
     */
    public Szoba getSzomszed(Szoba sz) {
        if (sz == egyikOldal) {
            if (merrolNyilik.get(egyikOldal)) return masikOldal;
            else return null;
        } else if (sz == masikOldal) {
            if (merrolNyilik.get(masikOldal)) return egyikOldal;
            else return null;
        }
        return null;
    }


    /**
     * Az ajtó osztály tick függvénye ami az ajtók időtől függő funkcioit hivja meg
     */
    public void tick() {
        villogas();
    }


    /**
     * Két ajtót hasonlít össze
     */
    public boolean equals(Ajto masikAjto) {
        return masikAjto.egyikOldal.equals(egyikOldal) && masikAjto.masikOldal.equals(masikOldal) ||
                masikAjto.egyikOldal.equals(masikOldal) && masikAjto.masikOldal.equals(egyikOldal);
    }
}