package pass.model.labyrinth;

import pass.model.CustomLogger;
import pass.model.human.Ember;
import java.util.*;
import java.util.logging.Level;
/* Az Ajtó osztály felelős az egyes szobákat összekötő ajtók kezeléséért és
működtetéséért a labirintusban. Feladata az ajtók állapotának nyomon követése,
valamint annak ellenőrzése, hogy nyitható-e a kért irányba az ajtó. Az Ajtó osztály
biztosítja a játékosok számára az átjutást a különböző szobák között azáltal, hogy
lehetővé teszi az áthaladást az ajtókon keresztül.  */
public class Ajto {
    private Szoba egyikOldal;
    private Szoba masikOldal;
    private boolean lathato = true;
    private Map<Szoba, Boolean> merreNyilik = new HashMap<>();
    private String nev;

    public List<String> getOldalak() {
        List<String> oldalak = new ArrayList<>();
        oldalak.add(egyikOldal.getNev());
        oldalak.add(masikOldal.getNev());
        return oldalak;
    }

    /**
     *
     * @param egyikOldal - Az egyik oldalán lévő szoba
     * @param masikOldal - A másik oldalán lévő szoba
     * @param nev - Szkeleton érdekében logoláshoz
     */
    public Ajto(Szoba egyikOldal, Szoba masikOldal, String nev){
        this.egyikOldal = egyikOldal;
        this.masikOldal = masikOldal;
        merreNyilik.put(egyikOldal, true);
        merreNyilik.put(masikOldal, true);
        this.nev = nev;
    }

    /**
     * A függvény kiírja az objektum nevét
     * @return String, Szkeleton kiiratashoz
     */
    public String toString(){
        return nev + " :Ajto";
    }


    /**
     * A függvény ha látható az ajtó akkor azpn keresztül a következő
     * szobába lépteti az embert és a kiinduló szobából eltávolítja
     * @param e - Az ember aki át kíván lépni a másik szobába, ez felelős a mozgatásáért.
     */
    public void hasznal(Ember e){
        if(e.getJelenlegiSzoba() != egyikOldal && e.getJelenlegiSzoba() != masikOldal) {
            System.out.println("NEM OTT VAN AZ AJTO AHOL A HALLGATO KOCSOG");
            return;
        }
        if(!lathato) {
            CustomLogger.info(e + " nem látja " + this +"-t, nem tud átmenni.");
            System.out.println(e + " nem látja " + this +"-t, nem tud átmenni.");
            return;
        }
        Szoba jelenlegiSzoba =  e.getJelenlegiSzoba();
        Szoba hovaMegy = (egyikOldal == jelenlegiSzoba) ? masikOldal : egyikOldal;
        if(merreNyilik.get(hovaMegy)){
            e.masikSzobabaLep(hovaMegy);
            jelenlegiSzoba.emberKivesz(e);
        }
        else {
            CustomLogger.info(this + " nem nyílik ebbe az irányba, " + e + " nem tud átmenni.");
            System.out.println(this + " nem nyílik ebbe az irányba, " + e + " nem tud átmenni.");
        }
    }

    /**
     * Setter függvény ami beállítja hogy merre nyílhat az ajtó
     * @param b1 Az egyik irányba nyílik-e
     * @param b2 A másik irányba nyílik-e
     */
    public void setMerreNyilik(boolean b1, boolean b2) {
        merreNyilik.clear();
        merreNyilik.put(egyikOldal, b1);
        merreNyilik.put(masikOldal, b2);
    }

    /**
     * Getter függvény ami visszaadja hogy látható-e az ajtó
     * @return láthatóságot visszaadja
     */
    public boolean getLathatosag(){
        return lathato;
    }

    /**
     *Setter függvény ami beállítja hogy látható-e az ajtó
     * @param a beállítja a láthatóságot
     */
    public void setLathatosag(boolean a){
        lathato = a;
    }

    /**
     *Ha az ajtó egyik oldalán egy átkozott szoba található
     * a szoba láthatósága változik
     */
    public void villogas() {
        if(!egyikOldal.atkozottE() && !masikOldal.atkozottE()){return;}
        if(Labirintus.getInstance().getTimeLeft() % 10 == 0)
            lathato = false;
        else if(Labirintus.getInstance().getTimeLeft() % 10 == 5)
            lathato = true;
    }


    /**
     * Afüggvény megváltoztatja a láthatóságát az ajtónak
     */
    public void lathatosagValtoztass(){
        if (lathato){
            lathato = false;
            CustomLogger.info(this + " láthatatlan lett.");
        }
        else if (!lathato){
            lathato = true;
            CustomLogger.info(this + " láthatóvá vált.");
        }
    }

    /**
     * Getter függvény ami visszadja a megadott szobából,
     * ezen az ajtón keresztül elérhető szomszédját
     * @param sz - Egy adott szoba.
     * @return Megadja egy adott ajtó másik oldalán lévő szobát
     */
    public Szoba getSzomszed(Szoba sz){
        if (sz == egyikOldal){
            return masikOldal;
        }
        else if (sz == masikOldal){
            return egyikOldal;
        }
        return null;
    }

    public void tick(){
        villogas();
    }

    public boolean equals(Ajto masikAjto){
        return ((this.egyikOldal == masikAjto.egyikOldal && this.masikOldal == masikAjto.masikOldal) || (this.egyikOldal == masikAjto.masikOldal && this.masikOldal == masikAjto.masikOldal));
    }
}