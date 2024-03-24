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
     * @param initialHealth - mennyi ideig tud védeni a gáz ellen
     * @param nev - az objektum neve
     */
    public Maszk(int initialHealth, String nev) {
        this.nev = nev;
        this.vedIdo = initialHealth;
    }

    /**
     *
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Maszk";
    }

    // -------------------------------
    private int vedIdo = 3;

    @Override
    public void hasznal() {
        // Nem lehet "hasznalni"
    }

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

    /**
     *
     * @param newSzoba - szobaváltás után itt lesz a tárgy
     */
    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        // Nem erdekes hol van
    }

    public int getVedIdo() {
        return vedIdo;
    }

    @Override
    public void tick() {
        vedIdo--;
    }
}