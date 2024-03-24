package pass.model.item;

import pass.model.CustomLogger;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;

/* A Camembert osztály felelős a mérgező gáz ideiglenes kibocsátásáért a labirintus, egy
a játékos által kiválasztott szobájába. */
public class Camembert implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;

    /**
     * A függvény elnevezi az objektumot
     * @param nev - az objektum neve
     */
    public Camembert(String nev) {
        this.nev = nev;
    }

    /**
     *
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Camembert";
    }

    // -------------------------------
    private Szoba szoba;
    private boolean poisonous = true;

    /**
     * A függvény a camambert használatát hajtja végre,
     * ezzel elgázosítva a szobát
     */
    public void hasznal() {
        if (poisonous) {
        szoba.setPoisonous(3);
        poisonous = false;
        CustomLogger.info(this + " használva");
        } else {
            CustomLogger.info(this + " már nem használható");
        }
    }

    /**
     *
     * @param visitor - a visitor amit fogad
     */
    @Override
    public void accept(TargyVisitor visitor) {
        CustomLogger.info(visitor + "-t " + this + "fogadta.");
    }

    /**
     * A függvény a tárgy tartzkodási helyét frissíti
     * @param newSzoba - szobaváltás után ebbe a szobába kerül a tárgy
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
