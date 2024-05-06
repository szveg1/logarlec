package pass.model.item;

import pass.model.CustomLogger;
import pass.model.TargyVisitor;
import pass.model.TargyVisitorGrafikus;
import pass.model.graphichelper.DrawObserver;

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
    public void accept(TargyVisitorGrafikus visitor) {
        visitor.visit(this);
    }

    /**
     * A függvény csökkenti a maszkon
     * hátralévő védelmi időt
     *
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

    @Override
    public void notifyObservers() {
        for (DrawObserver observer : observers) {
            observer.update(this);
        }
    }


    public int getVedIdo() {
        return vedIdo;
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