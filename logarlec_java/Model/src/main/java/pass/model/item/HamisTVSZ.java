package pass.model.item;

import pass.model.TargyVisitorGrafikus;

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
        return nev + " :HamisTVSZ";
    }

}
