package pass.model.item;

import pass.model.TargyVisitor;

public class HamisTVSZ extends Targy {
    /**
     * Konstruktor
     *
     * @param nev - az objektum neve
     */
    public HamisTVSZ(String nev) {
        this.nev = nev;
    }

    /**
     * TODO!!!
     *
     * @param visitor
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
        return nev + " :HamisTVSZ";
    }

}
