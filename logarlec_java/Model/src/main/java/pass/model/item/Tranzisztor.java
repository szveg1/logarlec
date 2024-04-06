package pass.model.item;

import pass.model.CustomLogger;
import pass.model.human.Oktato;
import pass.model.human.TargyVisitor;
import pass.model.labyrinth.Szoba;
import pass.model.human.Hallgato;
import pass.model.human.Ember;
/* A Tranzisztor osztály felelős a játékosok általi mozgás segítéséért. Segítségével a
játékosok képesek összekapcsolni két különböző szobát, hogy gyorsabban
mozoghassanak a labirintusban.  */
public class Tranzisztor implements Targy {
    // Csak szkeletonhoz-------------
    private String nev;

    /**
     * A függvény elnevezi az objektumot
     * @param nev - A tranzisztor neve, Szkeletonhoz.
     */
    public Tranzisztor(String nev) {
        this.nev = nev;
    }

    /**
     * A függvény kiírjaaz objektum nevét
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Tranzisztor";
    }

    // -------------------------------
    private Szoba jelenlegiSzoba;
    private Tranzisztor par = null;

    private Ember tulaj;

    /**
     * A függvény a párosítatlan Tranzisztorokat párosítja,
     * ha pedig már van párja, a tulajdonost oda teleportálja
     */
    @Override
    public void hasznal() {
        if (par == null) {
            CustomLogger.info(this + "-nek még nincs párja.");
            for (int i = 0; i < tulaj.getItems().size(); i++) {
                if (i != tulaj.getItems().indexOf(this)) {
                    tulaj.getItems().get(i).setPar(this);
                }
            }
        }
        else {
            CustomLogger.info(this + " már párosítva van.");
            tulaj.masikSzobabaLep(par.getSzoba());
        }

    }

    /**
     * Setter függvény ami a tranzisztornak egy párt állít be
     * @param t - A párnak beállítandó másik tranzisztor
     */
    @Override
    public void setPar(Tranzisztor t) {
        this.par = t;
        CustomLogger.info(this + " összepárosítva " + t + "ral.");
        if(t.par != this){
            t.setPar(this);
        }
    }

    /**
     * A függvény beállítja a tárgy új tartózkodási helyét a megadott szobára
     * @param newSzoba - Az új szoba, ahova átkerült
     */
    @Override
    public void szobaValtasrolErtesit(Szoba newSzoba) {
        jelenlegiSzoba = newSzoba;
    }

    /**
     * A függvény beállítja a tárgy új tulajdonosát a megadott emberre
     * @param newEmber - Az új ember, akihez átkerült
     */
    @Override
    public void emberValtasrolErtesit(Ember newEmber) {
        tulaj = newEmber;
    }

    /**
     * Getter függvény ami visszaadja a szobát amiben tartózkodik a tárgy
     * @return Visszaadja a szobát amiben tartózkodik a tárgy
     */
    public Szoba getSzoba() {
        return jelenlegiSzoba;
    }

}
