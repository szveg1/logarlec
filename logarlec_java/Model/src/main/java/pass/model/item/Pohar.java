package pass.model.item;

import pass.model.CustomLogger;
import pass.model.human.Oktato;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;
/* A Pohár osztály felelős azért, hogy tárolja és kezelje a söröspohár tulajdonságait és
funkcióit a labirintusban, mint például használható-e a tárgy védelem nyújtásra az
oktatókkal szemben.  */
public class Pohar implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;

    /**
     * A függvény elnevezi az objektumot
     * @param nev - Az objektum neve
     */
    public Pohar(String nev) {
        this.nev = nev;
    }

    /**
     * A függvény kiírjaaz objektum nevét
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Pohar";
    }

    // -------------------------------
    private int vedIdo = 3;

    @Override
    public void hasznal() {
        // Nem lehet használni.
    }

    /**
     * A függvény fogadja a visitet
     * @param visitor - A visitor, amit fogad
     */
    @Override
    public void accept(TargyVisitor visitor) {
        CustomLogger.info(visitor + "-t " + this + " fogadta.");
        visitor.visit(this);
    }

    /**
     *
     * @param newSzoba - Az új szoba, ahova átkerül
     */
    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {

    }

    /**
     *
     * @return Visszaadja hogy a pohár használható-e
     */
    public boolean hasznalhatoE() {
        return vedIdo > 0;
    }

    @Override
    public void tick() {

    }
}
