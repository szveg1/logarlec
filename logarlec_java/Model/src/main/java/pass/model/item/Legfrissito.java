package pass.model.item;

import pass.model.CustomLogger;
import pass.model.TargyVisitor;

public class Legfrissito extends Targy {
    private boolean hasznalva = false;

    /**
     * A függvény elnevezi az objektumot
     *
     * @param nev - az objektum neve
     */
    public Legfrissito(String nev) {
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

    @Override
    public void hasznal() {
        if (!hasznalva && jelenlegiSzoba.mergezoE()) {
            hasznalva = true;
            jelenlegiSzoba.setMeregIdo(0);
            tulajdonos.targyatEldob(this);
        }
    }

    public boolean hasznalhatoE() {
        return !hasznalva;
    }

    /**
     * A függvény kiírja az objektum nevét
     *
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Legfrissito";
    }

}
