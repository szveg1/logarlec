package pass.model.item;

import pass.model.TargyVisitorGrafikus;

public class HamisMaszk extends Targy {
    /**
     * Konstruktor
     *
     * @param nev - az objektum neve
     */
    public HamisMaszk(String nev) {
        this.nev = nev;
    }

    /**
     * A függvény fogadja a visitet
     *
     * @param visitor - A visitor, amit fogad
     */
    @Override
    public void accept(TargyVisitorGrafikus visitor) {
        visitor.visit(this);
    }



    /**
     * A függvény kiírja az objektum nevét
     *
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :HamisMaszk";
    }

    // -------------------------------
}
