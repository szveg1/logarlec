package pass.model.item;

import pass.model.CustomLogger;
import pass.model.TargyVisitorGrafikus;

/* A Camembert osztály felelős a mérgező gáz ideiglenes kibocsátásáért a labirintus, egy
a játékos által kiválasztott szobájába. */
public class Camembert extends Targy {
    private boolean mergezo = true;

    /**
     * Konstruktor
     *
     * @param nev - az objektum neve
     */
    public Camembert(String nev) {
        this.nev = nev;
    }

    /**
     * A függvény hatására a tárgy meglátogatásra kerül
     *
     * @param visitor - a látogató
     */
    @Override
    public void accept(TargyVisitorGrafikus visitor) {
        visitor.visit(this);
    }

    /**
     * A függvény a camambert használatát hajtja végre,
     * ezzel elgázosítva a szobát
     */
    public void hasznal() {
        if (mergezo) {
            CustomLogger.info(this + " használva");
            jelenlegiSzoba.setMeregIdo(3);
            mergezo = false;
        } else {
            CustomLogger.info(this + " már nem használható");
        }
    }



    /**
     * A függvény kiírja az objektum nevét
     *
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Camembert";
    }
}
