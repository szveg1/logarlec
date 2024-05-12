package pass.model.item;

import pass.model.CustomLogger;
import pass.model.TargyVisitor;
import pass.model.TargyVisitorGrafikus;

/***
 *  A Logarléc osztály felelős a játék céljának reprezentálásáért.
 *  A játékosok feladata a Logarléc felkutatása és megszerzése a labirintusban,
 *  annak érdekében, hogy nyerjenek.  */
public class Logarlec extends Targy {
    /**
     * Konstruktor
     *
     * @param nev - az objektum neve
     */
    public Logarlec(String nev) {
        this.nev = nev;
    }

    /**
     * TODO!!!
     *
     * @param visitor
     */
    @Override
    public void accept(TargyVisitorGrafikus visitor) {
        visitor.visit(this);
    }

    /**
     * @param visitor -  a visitor amit fogad
     */
    @Override
    public void accept(TargyVisitor visitor) {
        CustomLogger.info(visitor + "-t " + this + " fogadta.");
        visitor.visit(this);
    }

    public boolean hasznalhatoE() {
        return true;
    }

    /**
     * A függvény kiírja az objektum nevét
     *
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Logarlec";
    }

}
