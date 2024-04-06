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
    private Szoba szoba;

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
        szoba.setMeregIdo(0);
    }

    /**
     * @param newSzoba - Az új szoba, ahova átkerült
     */
    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        this.szoba = newSzoba;
        CustomLogger.info(this + " a " + szoba + "-ba került");
    }

    @Override
    public void tick() {

    }
}
