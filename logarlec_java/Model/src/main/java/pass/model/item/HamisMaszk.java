package pass.model.item;

import pass.model.CustomLogger;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

public class HamisMaszk implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;

    /**
     *
     * @param nev - az objektum neve
     */
    public HamisMaszk(String nev) {
        this.nev = nev;
    }

    /**
     * A függvény kiírja az objektum nevét
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :HamisMaszk";
    }

    // -------------------------------

    /**
     * @param visitor - a visitor amit fogad
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
