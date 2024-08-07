package pass.model.item;

import pass.model.CustomLogger;
import pass.model.TargyVisitor;
import pass.model.human.Oktato;

/**
 * Ez az osztály felelős a TVSZ kezeléséért és tulajdonságaiért a játékban,
 * védelem nyújtása és annak fentállásának ellenőrzésért
 */
public class TVSZ extends Targy {
    private int vedelmekSzama = 3;

    /**
     * Konstruktor
     *
     * @param nev - az objektum neve
     */
    public TVSZ(String nev) {
        this.nev = nev;
    }

    /**
     * @param visitor - A visitor, amit fogad.
     */
    @Override
    public void accept(TargyVisitor visitor) {
        CustomLogger.info(visitor + "-t " + this + " fogadta.");
        visitor.visit(this);
    }

    /**
     * A függvény a tvsz-t használja és csökkenti a hátralévő védelmek számát
     *
     * @param oktato - Az okató, aki ellen védekezik a használatával.
     */
    @Override
    public void hasznal(Oktato oktato) {
        if (vedelmekSzama > 0) {
            vedelmekSzama--;
        } else {
            return;
        }

        if (jelenlegiSzoba == null) {
            return;
        }
        jelenlegiSzoba.immunitastAd(oktato, oktato.getKitTamad());
    }



    /**
     * A függvény ellenőtzi hoyg a tárgy használható-e
     *
     * @return
     */
    public boolean hasznalhatoE() {
        return vedelmekSzama > 0;
    }

    public int getVedelmekSzama() { return vedelmekSzama; }

    /**
     * A függvény kiírja az objektum nevét
     *
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :TVSZ";
    }
}
