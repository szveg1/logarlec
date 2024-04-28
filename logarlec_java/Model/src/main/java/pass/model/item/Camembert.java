package pass.model.item;

import pass.model.CustomLogger;
import pass.model.labyrinth.Szoba;

/* A Camembert osztály felelős a mérgező gáz ideiglenes kibocsátásáért a labirintus, egy
a játékos által kiválasztott szobájába. */
public class Camembert implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;

    public String getNev() {
        return nev;
    }
    /**
     * A függvény elnevezi az objektumot
     * @param nev - az objektum neve
     */
    public Camembert(String nev) {
        this.nev = nev;
    }

    /**
     * A függvény kiírja az objektum nevét
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Camembert";
    }

    // -------------------------------
    private Szoba szoba;
    private boolean mergezo = true;

    /**
     * A függvény a camambert használatát hajtja végre,
     * ezzel elgázosítva a szobát
     */
    public void hasznal() {
        if (mergezo) {
            CustomLogger.info(this + " használva");
            szoba.setMeregIdo(3);
            mergezo = false;
        } else {
            CustomLogger.info(this + " már nem használható");
        }
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

}
