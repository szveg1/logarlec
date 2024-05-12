package pass.model.item;

import pass.model.CustomLogger;
import pass.model.TargyVisitor;
import pass.model.TargyVisitorGrafikus;

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
    public void accept(TargyVisitorGrafikus visitor) {
        visitor.visit(this);
    }

    /**
     * @param visitor - A visitor, amit fogad
     */
    @Override
    public void accept(TargyVisitor visitor) {
        CustomLogger.info(visitor + "-t " + this + " fogadta.");
        if (!hasznalva && jelenlegiSzoba.mergezoE()) {
            visitor.visit(this);
            hasznalva = true;
            jelenlegiSzoba.setMeregIdo(0);
        } else {
            CustomLogger.info(this + " már nem használható");
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
