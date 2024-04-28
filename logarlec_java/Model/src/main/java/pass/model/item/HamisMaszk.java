package pass.model.item;

import pass.model.CustomLogger;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

public class HamisMaszk implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;
    public String getNev() {
        return nev;
    }
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
}
