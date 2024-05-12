package pass.model.item;

import pass.model.TargyVisitor;

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
    public void accept(TargyVisitor visitor) {
        visitor.visit(this);
    }


    public boolean hasznalhatoE() {
        return true;
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
