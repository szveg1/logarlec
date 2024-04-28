package pass.model.item;

import pass.model.CustomLogger;
import pass.model.human.Oktato;
import pass.model.labyrinth.Labirintus;
import pass.model.labyrinth.Szoba;
import pass.model.human.TargyVisitor;
 /* A Logarléc osztály felelős a játék céljának reprezentálásáért. A játékosok feladata a
Logarléc felkutatása és megszerzése a labirintusban annak érdekében, hogy nyerjenek.  */
public class Logarlec implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;
     public String getNev() {
         return nev;
     }
    /**
     * A függvény elnevezi az objektumot
     * @param nev - az objektum neve
     */
    public Logarlec(String nev) {
        this.nev = nev;
    }

     /**
      * A függvény kiírjaaz objektum nevét
      * @return String, Szkeleton kiiratashoz
      */
    @Override
    public String toString() {
        return nev + " :Logarlec";
    }

    // -------------------------------

    /**
     *
     * @param visitor -  a visitor amit fogad
     */
    @Override
    public void accept(TargyVisitor visitor) {
        CustomLogger.info(visitor + "-t " + this + " fogadta.");
        visitor.visit(this);
    }

}
