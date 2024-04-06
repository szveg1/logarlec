package pass.model.item;

import pass.model.CustomLogger;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

public class Legfrissito implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;

    /**
     * A függvény elnevezi az objektumot
     * @param nev - az objektum neve
     */
    public Legfrissito(String nev) {
        this.nev = nev;
    }

    /**
     * A függvény kiírja az objektum nevét
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Legfrissito";
    }
    // -------------------------------

    private boolean hasznalva = false;
    private Szoba jelenlegiSzoba;

    /**
     * @param visitor - A visitor, amit fogad
     */
    @Override
    public void accept(TargyVisitor visitor) {
        CustomLogger.info(visitor + "-t " + this + " fogadta.");
        if(!hasznalva){
            visitor.visit(this);
            hasznalva = true;
        }
        else{
            CustomLogger.info(this + " már nem használható");
        }
        jelenlegiSzoba.setMeregIdo(0);
    }

    /**
     * @param newSzoba - Az új szoba, ahova átkerült
     */
    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        this.jelenlegiSzoba = newSzoba;
        CustomLogger.info(this + " a " + jelenlegiSzoba + "-ba került");
    }

}
