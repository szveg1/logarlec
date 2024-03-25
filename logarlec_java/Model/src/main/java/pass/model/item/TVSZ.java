package pass.model.item;

import pass.model.CustomLogger;
import pass.model.human.Oktato;
import pass.model.labyrinth.Szoba;
import pass.model.human.TargyVisitor;
/* Ez az osztály felelős a TVSZ kezeléséért és tulajdonságaiért a játékban, védelem
nyújtása és annak fentállásának ellenőrzésért  */
public class TVSZ implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;

    /**
     * A függvény elnevezi az objektumot
     * @param nev - az objektum neve
     */
    public TVSZ(String nev) {
        this.nev = nev;
    }

    /**
     * A függvény kiírjaaz objektum nevét
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :TVSZ";
    }

    // -------------------------------

    private Szoba jelenlegiSzoba;
    private int vedelmekSzama = 3;

    /**
     * A függvény a tvsz-t használja és csökkenti a hátralévő védelmek számát
     * @param oktato - Az okató, aki ellen védekezik a használatával.
     */
    @Override
    public void hasznal(Oktato oktato) {
        if(vedelmekSzama > 0){
            vedelmekSzama--;
        }else{
            return;
        }

        if (jelenlegiSzoba == null) {
            return;
        }
        jelenlegiSzoba.immunitastAd(oktato, oktato.getKitTamad());
    }

    /**
     *
     * @param visitor - A visitor, amit fogad.
     */
    @Override
    public void accept(TargyVisitor visitor) {
        CustomLogger.info(visitor + "-t " + this + " fogadta.");
        visitor.visit(this);
    }

    /**
     * A függvény beállítja a tárgy új tartózkodási helyét a megadott szobára
     * @param newSzoba - Az új szoba, ahova átkerült a tárgy.
     */
    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        jelenlegiSzoba = newSzoba;
    }

    public boolean hasznalhatoE() {
        return vedelmekSzama > 0;
    }

    @Override
    public void tick() {

    }
}
