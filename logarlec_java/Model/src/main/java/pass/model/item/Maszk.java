package pass.model.item;

import pass.model.CustomLogger;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

/* A Maszk osztály felelős a játékosok védelméért a mérgező gázokkal teli szobákban. A
maszk egy adott ideig tudja csak megakadályozni a mérges gázok belélegzését.  */
public class Maszk implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;

    /**
     *
     * @param vedIdo - mennyi ideig tud védeni a gáz ellen
     * @param nev - az objektum neve
     */
    public Maszk(int vedIdo, String nev) {
        this.nev = nev;
        this.vedIdo = vedIdo;
    }

    /**
     * A függvény kiírja az objektum nevét
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Maszk";
    }

    // -------------------------------
    private int vedIdo;


    /**
     * A függvény csökkenti a maszkon
     * hátralévő védelmi időt
     * @param visitor - a visitor amit fogad
     */
    @Override
    public void accept(TargyVisitor visitor) {
        CustomLogger.info(visitor + "-t " + this + " fogadta.");
        if (vedIdo > 0) {
            CustomLogger.info(this + " még használható");
            visitor.visit(this);
            vedIdo--;
        } else {
            CustomLogger.info(this + " már nem használható");
        }
    }

    public int getVedIdo() {
        return vedIdo;
    }

}