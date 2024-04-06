package pass.model.item;

import pass.model.CustomLogger;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

public class HamisLec implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;

    /**
     * A függvény elnevezi az objektumot
     * @param nev - az objektum neve
     */
    public HamisLec(String nev) {
        this.nev = nev;
    }

    /**
     * A függvény kiírjaaz objektum nevét
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :HamisLec";
    }

    // -------------------------------

    /**
     *
     * @param visitor -  a visitor amit fogad
     */
    @Override
    public void accept(TargyVisitor visitor) {
        CustomLogger.info(visitor + "-t " + this + " fogadta.");
        visitor.visit(this);
    }

    @Override
    public void tick() {
        // Tickeles nem szukseges
    }
}
