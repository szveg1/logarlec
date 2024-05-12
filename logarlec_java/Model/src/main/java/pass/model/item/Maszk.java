package pass.model.item;

import pass.model.CustomLogger;
import pass.model.TargyVisitor;

/* A Maszk osztály felelős a játékosok védelméért a mérgező gázokkal teli szobákban. A
maszk egy adott ideig tudja csak megakadályozni a mérges gázok belélegzését.  */
public class Maszk extends Targy {
    private int vedIdo;

    /**
     * Konstruktor
     *
     * @param vedIdo - mennyi ideig tud védeni a gáz ellen
     * @param nev    - az objektum neve
     */
    public Maszk(int vedIdo, String nev) {
        this.nev = nev;
        this.vedIdo = vedIdo;
    }

    @Override
    public void accept(TargyVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * A függvény csökkenti a maszkon
     * hátralévő védelmi időt
     *
     */
    public void csokkentVedIdo() {vedIdo--; }

    public int getVedIdo() {
        return vedIdo;
    }

    public boolean hasznalhatoE() {
        return vedIdo > 0;
    }

    /**
     * A függvény kiírja az objektum nevét
     *
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Maszk";
    }
}