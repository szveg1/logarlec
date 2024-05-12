package pass.model.item;

import pass.model.CustomLogger;
import pass.model.TargyVisitorGrafikus;
import pass.model.human.Ember;
import pass.model.labyrinth.Szoba;

/**
 * A Tranzisztor osztály felelős a játékosok általi mozgás segítéséért.
 * Segítségével a játékosok képesek összekapcsolni két különböző szobát,
 * hogy gyorsabban mozoghassanak a labirintusban.
 */
public class Tranzisztor extends Targy {
    private Tranzisztor par = null;

    /**
     * Konstruktor
     *
     * @param nev - A tranzisztor neve, Szkeletonhoz.
     */
    public Tranzisztor(String nev) {
        this.nev = nev;
    }

    /**
     * TODO!!!
     *
     * @param visitor - A visitor, amit fogad
     */
    @Override
    public void accept(TargyVisitorGrafikus visitor) {
        visitor.visit(this);
    }

    /**
     * A függvény a párosítatlan Tranzisztorokat párosítja,
     * ha pedig már van párja, a tulajdonost oda teleportálja
     */
    @Override
    public void hasznal() {
        if (par == null) {
            CustomLogger.info(this + "-nek még nincs párja.");
            for (int i = 0; i < tulajdonos.getItems().size(); i++) {
                if (i != tulajdonos.getItems().indexOf(this)) {
                    tulajdonos.getItems().get(i).setPar(this);
                }
            }
        } else {
            CustomLogger.info(this + " már párosítva van.");
            tulajdonos.masikSzobabaLep(par.getSzoba());
            par.par = null;
            par = null;

        }

    }

    public boolean hasznalhatoE() {
        return true;
    }


    /**
     * Setter függvény ami a tranzisztornak egy párt állít be
     *
     * @param t - A párnak beállítandó másik tranzisztor
     */
    @Override
    public void setPar(Tranzisztor t) {
        this.par = t;
        CustomLogger.info(this + " összepárosítva " + t + "ral.");
        if (t.par != this) {
            t.setPar(this);
        }
    }


    /**
     * Getter függvény ami visszaadja a szobát amiben tartózkodik a tárgy
     *
     * @return Visszaadja a szobát amiben tartózkodik a tárgy
     */
    public Szoba getSzoba() {
        return jelenlegiSzoba;
    }


    /**
     * A függvény kiírja az objektum nevét
     *
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Tranzisztor";
    }
}
