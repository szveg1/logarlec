package pass.model.item;

import pass.model.CustomLogger;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

public class HamisLec implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;
    public String getNev() {
        return nev;
    }
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
}
